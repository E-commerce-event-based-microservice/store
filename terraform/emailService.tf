locals{
     emailServiceContainerName = "emailService"
     emailServiceContainerPort = 80
     emailServiceImageURI = "docker.io/abood1/email_service"
}



resource "aws_ecs_task_definition" "emailService" {
  family             = "emailService"
  # allows your Amazon ECS container task to make calls to other AWS services.
  task_role_arn      = aws_iam_role.ecs_task_role.arn
#ARN of the task execution role that the Amazon ECS container agent and the Docker daemon can assume.
  execution_role_arn = aws_iam_role.ecs_exec_role.arn
  network_mode       = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                = 256
  memory             = 512
  container_definitions = jsonencode([{
    name         = local.emailServiceContainerName,
    # image        = "${aws_ecr_repository.app.repository_url}:latest",
    image        = "${local.emailServiceImageURI}"
    essential    = true,
    portMappings = [{ containerPort = local.emailServiceContainerPort, hostPort = 80 }],
    environment = [
      { "name":"KAFKA_HOST", "value": "${aws_instance.kafka.public_dns}"}

    ],
     "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
          "awslogs-group": "${aws_cloudwatch_log_group.log-group.id}",
          "awslogs-region": "${var.aws_region}",
          "awslogs-stream-prefix": "store"
        }
      },
    depends_on = [aws_instance.kafka]
  }])
}


# ECS emailService
resource "aws_ecs_service" "emailService" {
  name                 = "emailService"
  cluster              = aws_ecs_cluster.store-fargate-cluster.id
  task_definition      = aws_ecs_task_definition.emailService.arn
  launch_type          = "FARGATE"
  scheduling_strategy  = "REPLICA"
  desired_count        = 2
  deployment_minimum_healthy_percent = 50
  #deployment_maximum_percent = 100     
  force_new_deployment = true

  network_configuration {
    subnets          = aws_subnet.private.*.id
    assign_public_ip = false
    #   aws_security_group.service_security_group.id,
    security_groups = [ aws_security_group.load_balancer_security_group.id, aws_security_group.kafka_access_sg.id ]
  }

   # associate a service with target group 
  load_balancer {
    target_group_arn = aws_lb_target_group.emailService_target_group.arn
    # Name of the container to associate with the load balancer (as it appears in a container definition)
    container_name   = local.emailServiceContainerName
    container_port   = local.emailServiceContainerPort
  }

  depends_on = [aws_alb_listener.listener]
}

