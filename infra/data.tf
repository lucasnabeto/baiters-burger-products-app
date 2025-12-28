data "aws_iam_role" "lab_role" {
  name = "LabRole"
}

data "aws_ecr_repository" "repository" {
  name = "baitersburger-products-repository"
}

data "aws_ecs_cluster" "baitersburger_products_cluster" {
  cluster_name = "baitersburger-products-cluster"
}

data "aws_vpc" "vpc_default" {
  default = true
}

data "aws_subnets" "all_default_subnets" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.vpc_default.id]
  }
}

data "aws_security_group" "alb_sg" {
  name = "alb-sg"
}