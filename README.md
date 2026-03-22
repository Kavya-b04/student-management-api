# Student Management API

REST API built with Spring Boot, MySQL, JWT Authentication.

## Tech Stack
- Java 21
- Spring Boot 3.2
- Spring Security + JWT
- MySQL + Spring Data JPA
- Lombok + MapStruct
- Maven

## Features
- Full CRUD for students
- JWT authentication (register / login)
- Role-based access (USER / ADMIN)
- Input validation + global error handling
- Pagination and sorting

## API Endpoints

### Auth
| Method | Endpoint                    | Access |
|--------|-----------------------------|--------|
| POST   | /api/v1/auth/register       | Public |
| POST   | /api/v1/auth/login          | Public |
| POST   | /api/v1/auth/register/admin | Public |

### Students
| Method | Endpoint                | Access     |
|--------|-------------------------|------------|
| GET    | /api/v1/students        | USER+ADMIN |
| GET    | /api/v1/students/{id}   | USER+ADMIN |
| POST   | /api/v1/students        | USER+ADMIN |
| PUT    | /api/v1/students/{id}   | USER+ADMIN |
| DELETE | /api/v1/students/{id}   | ADMIN only |

## Run Locally
1. Clone the repo
2. Create MySQL database: `CREATE DATABASE student_db;`
3. Create `application-local.properties` with your DB credentials
4. Run: `mvn spring-boot:run`
