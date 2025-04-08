# API-Scrum-Management
API Scrum Management
The API Scrum Management system allows you to manage Scrum projects, epics, user stories, sprints, and other key elements. It provides functionality for Scrum Managers, Product Owners, and Developers, facilitating task management and project planning in an agile environment.

## Main Features

-  CRUD operations for Scrum entities: 
    Project, Product Backlog, Sprint, Sprint Backlog, Epic, User Story, Task

-  Authentication and authorization with Spring Security (roles: Product Owner, Scrum Manager, Developer)

-   Unit tests with JUnit and Mockito

-  Swagger documentation for testing API endpoints

## Technologies Used

- **Spring Boot**: 
   Main framework for backend development

- **Spring Data JPA**:
   Database access and management

- **Spring Security**: 
   Authentication and authorization management

- **Swagger / OpenAPI**: 
   API documentation and endpoint testing

- **JUnit & Mockito**: 
   For writing and running unit tests

## Installation
### Prerequisites

Before you begin, ensure you have the following installed:
- [JDK 17 or later](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)


1. Navigate to the project directory :
```bash
   cd API-Scrum-Management
```
2. Build the project with Maven :
```bash
   mvn clean install
```
3. Run the application :
```bash
  mvn spring-boot:run
```
4. Access Swagger UI :
```bash
  http://localhost:8081/swagger-ui/
```