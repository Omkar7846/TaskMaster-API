# TaskMaster - Project Management REST API

TaskMaster is a Spring Boot 3 based RESTful API designed to help users manage projects and tasks efficiently.
It features secure JWT authentication, data isolation per user, and advanced search/sorting capabilities.

##  Features
- **User Authentication:** Secure JWT-based registration and login system.
- **Project Management:** Create, Read, Update, and Delete (CRUD) projects.
- **Task Management:** Manage tasks within projects with status and priority tracking.
- **Advanced Search:** Global search across all user projects by title or description.
- **Dynamic Sorting:** Sort tasks by due date or priority levels.
- **Global Exception Handling:** Structured error responses for validation and security failures.

##  Tech Stack
- **Java:** 17+
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security & JWT
- **Database:** MySQL 8.0
- **Documentation:** Postman Collection

---

## Setup Instructions

### 1. Prerequisites
- Install **Java 17** or higher.
- Install **Maven**.
- Install **MySQL Server**.

### 2. Database Configuration
1. Log into MySQL and create the database:
   ```sql
   CREATE DATABASE taskmaster;


### Database Schema
The application uses a relational schema with three main entities:
1. User: Stores credentials and profile data.
2. Project: Linked to a User (One-to-Many).
3. Task: Linked to a Project (Many-to-One). Contains fields: id, title, description, status, priority, dueDate, createdAt, updatedAt.

### API Endpoints

1. Authentication (Public)
Method,Endpoint,Description
POST,/api/auth/register,Register a new user account
POST,/api/auth/login,Login and receive a JWT Bearer Token

2. Project Management (Authenticated)
   Method,Endpoint,Description
POST,/api/projects,Create a new project
GET,/api/projects,Get all projects owned by the user
PUT,/api/projects/{id},Update project details
DELETE,/api/projects/{id},Delete a project and its tasks

3. Task Management (Authenticated)
   Method,Endpoint,Description
POST,/api/projects/{projectId}/tasks,Add a task to a specific project
GET,/api/projects/{projectId}/tasks,List all tasks for a project
GET,/api/tasks/search,"Search all tasks (Params: keyword, sortBy)"
PUT,/api/tasks/{taskId},Update task details/status
DELETE,/api/tasks/{taskId},Delete a specific task
   
