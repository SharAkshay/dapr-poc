# Dapr Microservices with Docker Compose
This project demonstrates a microservices architecture using Dapr, Spring Boot, and Docker Compose. It consists of two services, service-a and service-b, each running with its own Dapr sidecar. The services communicate with each other using Dapr's service invocation API, and service-b logs messages to a MySQL database.


## Architecture Overview

- **Service A**: Sends a ping request to service-b using Dapr's service invocation.
- **Service B**: Receives the ping request, processes it, and logs the message to a MySQL database.
- **Dapr Sidecars**: Each service has a Dapr sidecar (service-a-dapr and service-b-dapr) to handle service discovery and communication.
- **MySQL Database**: Used by service-b to store request details.

## Prerequisites

- Docker Desktop installed
- curl installed for testing endpoints
- MySQL client (optional, for database inspection)

## Configuration

### Environment Variables

The .env file contains the following configurations:

```plaintext
# .env.example
MYSQL_ROOT_PASSWORD=<your_mysql_root_password>
MYSQL_DATABASE=<your_database_name>
MYSQL_USER=<your_database_user>
MYSQL_PASSWORD=<your_database_password>
SPRING_DATASOURCE_URL=jdbc:mysql://<your_mysql_host>:3306/<your_database_name>
SPRING_DATASOURCE_USERNAME=<your_database_user>
SPRING_DATASOURCE_PASSWORD=<your_database_password>
DAPR_CLIENT_ENABLED=false
```

## Docker Compose Network

All containers are connected to the dapr-network defined in the docker-compose.yaml file.


## Getting Started

Follow these steps to build, run, and test the application:

### Step 1: Stop and Remove Existing Containers

```bash
docker-compose down -v
```

### Step 2: Build and Start All Containers

```bash
docker-compose up --build
```

### Step 3: Verify Network Connectivity

Ensure all containers are in the same network:

```bash
docker network inspect msuk-dapr_dapr-network
```

### Step 4: Health Checks for Services
Check the health of both services:

```bash
curl http://localhost:5001/actuator/health
curl http://localhost:5002/actuator/health
```

### Step 5: Test Service-B Dapr Sidecar

Invoke service-b through its Dapr sidecar:

```bash
curl -X POST http://localhost:3501/v1.0/invoke/service-b/method/service-b/ping \
-H "Content-Type: application/json" \
-d '{"message": "test ping from terminal"}'
```

### Step 6: Test Service-A to Service-B Communication

From service-a, invoke service-b through its Dapr sidecar:

```bash
curl -X POST http://service-b-dapr:3501/v1.0/invoke/service-b/method/service-b/ping \
-H "Content-Type: application/json" \
-d '{"message": "ping from Service A container"}'
```

### Step 7: Verify Database Entries

Check if the messages are logged in the MySQL database:

```bash
docker exec -it mysql mysql -u dapr -p

USE dapr_poc;
SELECT * FROM request_details;
```

### Step 8: Test Service-A Dapr Sidecar

Invoke service-a through its Dapr sidecar:

```bash
curl -X GET http://localhost:3500/v1.0/invoke/service-a/method/service-a/ping
```

## Additional Notes

- **Dapr Ports**:
    - `3500`: Dapr HTTP API for `service-a`
    - `3501`: Dapr HTTP API for `service-b`
- **MySQL**:
    - Database: Refer to the `.env` file for the database name.
    - User: Refer to the `.env` file for the database username.
    - Password: Refer to the `.env` file for the database password.

## Cleanup

To stop and remove all containers, networks, and volumes:

```bash
docker-compose down -v
```