variable "docker_image_tag" {
  description = "Docker image tag (commit SHA)"
  type        = string
}

variable "database_connection_url" {
  description = "Database connection URL"
  type        = string
  sensitive = true
}

variable "database_driver_class_name" {
    description = "Database driver class name"
    type        = string
}

variable "database_hibernate_dialect" {
    description = "Database Hibernate dialect"
    type        = string
}