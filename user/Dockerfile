# Use Maven base image to build the project
FROM maven:3.8.1-jdk-11 as build
WORKDIR /app
# Copy the pom.xml to fetch all dependencies
COPY pom.xml .
COPY src src
# Package the application
RUN mvn clean package -DskipTests

# Use OpenJDK for running the application
FROM openjdk:11
COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar /user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]

