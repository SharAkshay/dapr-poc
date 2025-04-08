# Use Java 17 base image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the Spring Boot JAR file
COPY target/service-b-0.0.1-SNAPSHOT.jar service-b.jar

# Expose the application port
EXPOSE 5002

# Run the application using the Java command
ENTRYPOINT ["java", "-jar", "service-b.jar"]