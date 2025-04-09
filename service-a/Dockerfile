# Use Java 17 base image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Copy the Spring Boot JAR file
COPY target/service-a-0.0.1-SNAPSHOT.jar service-a.jar

# Expose the application port
EXPOSE 5001

# Run the application using the Java command
ENTRYPOINT ["java", "-jar", "service-a.jar"]