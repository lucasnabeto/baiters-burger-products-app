resource "aws_ecs_task_definition" "app" {
  family                   = "baitersburger-products-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = data.aws_iam_role.lab_role.arn
  task_role_arn            = data.aws_iam_role.lab_role.arn

  container_definitions = jsonencode([
    {
      name      = "baitersburger-products-container"
      image     = "${data.aws_ecr_repository.repository.repository_url}:${var.docker_image_tag}"
      essential = true

      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]

      environment = [
        {
          name  = "DATABASE_DRIVER_CLASS_NAME"
          value = var.database_driver_class_name
        },
        {
          name  = "DATABASE_HIBERNATE_DIALECT"
          value = var.database_hibernate_dialect
        }
      ]

      secrets = [
        {
          name      = "DATABASE_CONNECTION_URL"
          valueFrom = aws_ssm_parameter.database_connection_url.arn
        },
        {
          name      = "DATABASE_USERNAME"
          valueFrom = "${data.aws_secretsmanager_secret.secret_rds_credentials.arn}:username::"
        },
        {
          name      = "DATABASE_PASSWORD"
          valueFrom = "${data.aws_secretsmanager_secret.secret_rds_credentials.arn}:password::"
        }
      ]

      healthCheck = {
        command     = ["CMD-SHELL", "wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1"]
        interval    = 30
        timeout     = 5
        retries     = 3
        startPeriod = 90
      }

      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = "/ecs/baitersburger-products"
          "awslogs-region"        = "us-east-1"
          "awslogs-stream-prefix" = "ecs"
          "awslogs-create-group"  = "true"
        }
      }
    }
  ])
}

resource "aws_ecs_service" "service" {
  name            = "baitersburger-products-service"
  cluster         = data.aws_ecs_cluster.baitersburger_products_cluster.arn
  task_definition = aws_ecs_task_definition.app.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = data.aws_subnets.all_default_subnets.ids
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = data.aws_lb_target_group.baitersburger_products_tg.arn
    container_name   = "baitersburger-products-container"
    container_port   = 8080
  }
}

resource "aws_security_group" "ecs_sg" {
  name        = "ecs-tasks-sg"
  vpc_id      = data.aws_vpc.vpc_default.id
  description = "Only the load balancer is able to access ECS tasks"
}

resource "aws_vpc_security_group_ingress_rule" "ecs_ingress_from_alb" {
  security_group_id            = aws_security_group.ecs_sg.id
  referenced_security_group_id = data.aws_security_group.alb_sg.id
  from_port                    = 8080
  to_port                      = 8080
  ip_protocol                  = "tcp"
}

resource "aws_vpc_security_group_egress_rule" "ecs_egress_all_out" {
  security_group_id = aws_security_group.ecs_sg.id
  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "-1"
}

resource "aws_ssm_parameter" "database_connection_url" {
  name  = "/rds/database_connection_url"
  type  = "SecureString"
  value = var.database_connection_url
}