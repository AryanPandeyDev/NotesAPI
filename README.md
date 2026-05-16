# notesapi

Notes API built with Spring Boot, JWT authentication, and MySQL. Supports local development and Docker Compose.

## Features
- User registration and login
- JWT-based authentication
- Notes CRUD (secured)
- MySQL persistence via JPA/Hibernate

## Tech Stack
- Java 17
- Spring Boot 4
- MySQL 8+ (or MySQL latest via Docker)
- jjwt (JWT token generation/validation)

## Project Structure
```
src/
  main/
    java/com/example/simpleAPI
    resources/application.properties
  test/
```

## Quick Start (Docker Compose)
> Use this if you want the full stack (app + MySQL) with zero local DB setup.

1) Build the jar:

```powershell
cd C:\Projects\simpleAPI
.\mvnw.cmd -DskipTests clean package
```

2) Start the stack:

```powershell
docker compose up --build
```

3) Verify services:

```powershell
docker compose ps
```

App is available at:
- `http://localhost:8080`

To stop:

```powershell
docker compose down
```

## Local Run (without Docker)
> Use this if you have MySQL installed locally.

1) Create a database:

```sql
CREATE DATABASE IF NOT EXISTS NotesDB;
```

2) Update `src/main/resources/application.properties` if needed:

```ini
spring.datasource.url=jdbc:mysql://localhost:3306/NotesDB
spring.datasource.username=root
spring.datasource.password=pandey13
jwt.secret=YOUR_LONG_RANDOM_SECRET
jwt.expiration-ms=3600000
```

3) Run the app:

```powershell
.\mvnw.cmd spring-boot:run
```

## Configuration
Spring Boot reads properties from `application.properties` and environment variables. Environment variables override file values.

### Key Properties
```ini
jwt.secret=YOUR_LONG_RANDOM_SECRET
jwt.expiration-ms=3600000
spring.jpa.hibernate.ddl-auto=update
```

### Docker Compose Environment Overrides
```text
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/NotesDB
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=pandey13
JWT_SECRET=YOUR_LONG_RANDOM_SECRET
JWT_EXPIRATION_MS=3600000
```

## Auth Flow (JWT)
1) Register user (`/user/register`)
2) Login (`/user/login`) → returns JWT
3) Send token on all protected requests:

```
Authorization: Bearer <token>
```

## API Endpoints (core)
### Auth
- `POST /user/register`
- `POST /user/login`

### Users
- `GET /user/getAllUser` (secured)

### Notes
- `POST /notes/create` (secured)
- `GET /notes/getUserNotes/{userId}` (secured)
- `PUT /notes/update/{noteId}` (secured)
- `DELETE /notes/delete/{noteId}` (secured)

## Example Requests
### Register
```http
POST /user/register
Content-Type: application/json

{
  "username": "test",
  "email": "test@example.com",
  "password": "test123"
}
```

### Login
```http
POST /user/login
Content-Type: application/json

{
  "username": "test",
  "email": "test@example.com",
  "password": "test123"
}
```

### Authorized Request
```http
GET /notes/getUserNotes/1
Authorization: Bearer <token>
```

## Common Issues
### MySQL connection fails in Docker
- Ensure DB host is `mysql` (not `localhost`) inside containers.
- If MySQL doesn’t start, port `3306` may already be in use. Update the host port mapping in `docker-compose.yml` (e.g., `3307:3306`).

### JWT errors
- Ensure `jwt.secret` is set (32+ characters recommended).
- Tokens expire based on `jwt.expiration-ms`.

## Tests
```powershell
.\mvnw.cmd test
```
