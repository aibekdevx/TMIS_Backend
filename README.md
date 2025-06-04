# TMIS — Teacher Management Information System

TMIS is an academic information system built to manage and store data about teachers of the Computer Engineering department.  
The system includes a secure backend with role-based access control, and a responsive web frontend for admins and teachers.

---

## 📦 Monorepo Structure

```
TMIS/
├── backend/          # Spring Boot app (AuthService)
├── frontend/         # React.js frontend

```

---

## ⚙️ Backend — Spring Boot

### 🛠 Stack

- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- Maven

### 📁 Structure

```
src/main/java/com/diploma/authservice
├── auth          # Auth controller and JWT logic
├── config        # Security configuration
├── controller    # API endpoints
├── dto           # Request/response DTOs
├── entity        # JPA entities (User, Teacher, etc.)
├── repository    # Spring Data JPA Repositories
├── service       # Business logic
├── user          # Role, User entity, and repo
````

### 🚀 Run Backend Locally

1. **Go to backend folder:**

```bash
cd backend
````

2. **Set up PostgreSQL:**

Create a database and update `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tmis
    username: postgres
    password: your_password
```

3. **Run the app:**

```bash
./mvnw spring-boot:run
```

4. **Swagger UI:**

Visit [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🌐 Frontend — React.js

### 🛠 Stack

* React.js
* React Router
* Tailwind CSS
* Axios
* jwt-decode
* React Icons

### 📁 Structure

```
src/
├── Components/
│   ├── Tabs/                  # Teacher profile tabs
│   ├── AddDisciplineModal/   # Modal for discipline assign
│   ├── Sidebar, Navbar, etc. # Layout components
├── Context/                  # Global state (auth)
├── Pages/                    # Main UI pages
├── Routes/                   # Route configuration
├── App.js / index.js         # App entry
```

### 🚀 Run Frontend Locally

1. **Go to frontend folder:**

```bash
cd frontend
```

2. **Install dependencies:**

```bash
npm install
```

3. **Set up environment file:**

Create `.env`:

```
REACT_APP_API_URL=http://localhost:8080/api/v1
```

4. **Run the app:**

```bash
npm start
```

5. **Visit:**

[http://localhost:3000](http://localhost:3000)

---

## 🔐 Authentication Flow

* JWT is issued upon login and stored in `localStorage`
* Decoded using `jwt-decode` to get user role (`ADMIN`, `TEACHER`, etc.)
* Protected frontend routes based on role
* Backend verifies JWT on each request

---

## 📑 Functionality Overview

### Admin Features:

* View list of all teachers
* Add/edit/delete teacher data:

  * Personal info
  * Education
  * Academic degrees & titles
  * Disciplines
  * Job info
  * Foreign cognition / Inclusive education

### Teacher Features:

* View and update own profile
* Add personal achievements, education, etc.
* Read-only access to discipline list

---

## 🧪 Testing

* Backend: Unit tests (Spring Boot Test)
* Frontend: Jest (basic)
* Manual QA with Postman and UI test cases

---

## 📸 Demo

---

## 🧑‍💻 Team

* **Aibek Shynazbek** — Backend Developer, System Architect
* **Dimash** — Frontend Developer, Requirements & UI/UX
* **Nurkanat** — QA Engineer, Stack Analyst, Documentation

---

## 📄 License

This project is part of a diploma thesis and not intended for commercial use.

```

