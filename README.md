# TaskMaster - Project Management REST API

TaskMaster is a Spring Boot 3 based RESTful API designed to help users manage projects and tasks efficiently.
It features secure JWT authentication, data isolation per user, and advanced search/sorting capabilities.

## 🚀 Features
- **User Authentication:** Secure JWT-based registration and login system.
- **Project Management:** Create, Read, Update, and Delete (CRUD) projects.
- **Task Management:** Manage tasks within projects with status and priority tracking.
- **Advanced Search:** Global search across all user projects by title or description.
- **Dynamic Sorting:** Sort tasks by due date or priority levels.
- **Global Exception Handling:** Structured error responses for validation and security failures.

## 🛠️ Tech Stack
- **Java:** 17+
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security & JWT
- **Database:** MySQL 8.0
- **Documentation:** Postman Collection

---

## ⚙️ Setup Instructions

### 1. Prerequisites
- Install **Java 17** or higher.
- Install **Maven**.
- Install **MySQL Server**.

### 2. Database Configuration
1. Log into MySQL and create the database:
   ```sql
   CREATE DATABASE taskmaster;
