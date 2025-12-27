resource "aws_ecs_task_definition" "app" {
  family                   = "baitersburger-products-app"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "1024" # 1 vCPU
  memory                   = "2048" # 2 GB
  execution_role_arn       = data.aws_iam_role.lab_role.arn

  container_definitions = jsonencode([
    {
      name      = "baitersburger-products-container"
      image     = "${data.aws_ecr_repository.repository.repository_url}:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
    }
  ])
}

resource "aws_ecs_service" "service" {
  name            = "java-spring-service"
  cluster         = data.aws_ecs_cluster.baitersburger_products_cluster.arn
  task_definition = aws_ecs_task_definition.app.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = data.aws_subnets.all_default_subnets.ids
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = false
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
  from_port                    = 80
  to_port                      = 80
  ip_protocol                  = "tcp"
}

resource "aws_vpc_security_group_egress_rule" "ecs_egress_all_out" {
  security_group_id = aws_security_group.ecs_sg.id
  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "-1"
}