# task-manager-api

Overview
The Task Manager API is a Spring Boot application that allows users to manage tasks and assign them to specific users. The API is backed by a PostgreSQL database and handles time zones appropriately by converting all dates and times to UTC.

Features -
Create, retrieve, update, and delete tasks.
Assign tasks to users.
Handle time zones for tasks, storing dates in UTC.
User management: Create, update, delete, and retrieve users.
Basic validation and error handling.

Prerequisites -
Before running the project, ensure you have the following installed:
Java 17 (or higher)
Gradle (for building the project)
PostgreSQL (for the database)
Git (optional, for version control)

Setup and Installation
1. Clone the Repository-
   git clone https://github.com/Ridhi1001/task-manager-api.git
   cd task-manager-api
2. Configure the Database
Create a PostgreSQL Database:
Log in to PostgreSQL and create a database:
    CREATE DATABASE task_manager_db;
Update application.properties:
In src/main/resources/application.properties, configure the database connection:
spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
3. Build and Run the Application
Navigate to the project directory and run the following commands:
 ./gradlew build
 ./gradlew bootRun
The application will start on http://localhost:8080.

4. Test the API
You can test the API using Postman or any other API testing tool. Use the following endpoints:

Create a Task: POST /api/tasks
Retrieve All Tasks: GET /api/tasks
Retrieve a Task by ID: GET /api/tasks/{id}
Update a Task: PUT /api/tasks/{id}
Delete a Task: DELETE /api/tasks/{id}
Create a User: POST /api/users
Retrieve All Users: GET /api/users
Retrieve a User by ID: GET /api/users/{id}
Update a User: PUT /api/users/{id}
Delete a User: DELETE /api/users/{id}

Error Handling
The API returns appropriate HTTP status codes and error messages for various scenarios, including:

404 Not Found: When a task or user is not found.
400 Bad Request: When validation fails.
500 Internal Server Error: For any unexpected errors.


  
