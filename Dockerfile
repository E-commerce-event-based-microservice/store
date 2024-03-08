# Step 1: Build the entire Maven project
FROM maven:3.8.1-jdk-11 as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

# Step 2: Build specific service image (for inventory service, for example)
FROM openjdk:11-jre-slim
COPY --from=builder /usr/src/app/inventory/target/inventory-*.jar /app/inventory-service.jar
ENTRYPOINT ["java", "-jar", "/app/inventory-service.jar"]
