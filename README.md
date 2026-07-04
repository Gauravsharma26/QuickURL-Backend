<div align="center">

# 🚀 QuickURL Backend

**A secure, scalable URL shortening platform built with Spring Boot**

Provides REST APIs for user authentication, URL shortening, redirection, and click analytics.

[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?logo=postgresql)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[Live Demo](#-live-api) · [Features](#-features) · [API Reference](#-api-endpoints) · [Getting Started](#-running-locally)

<br>

<img width="850" alt="QuickURL Image" src="https://raw.githubusercontent.com/Gauravsharma26/QuickURL-Backend/main/Screenshot%202026-07-04%20173724.png" />

</div>

---

## 🖼️ Images

<div align="center">

<img width="850" alt="QuickURL Dashboard Image" src="https://raw.githubusercontent.com/Gauravsharma26/QuickURL-Backend/main/Screenshot%202026-07-04%20174442.png" />

</div>

---

## 🌐 Live API

> **⚠️ Note:** The backend is hosted on Render's free tier. The first request after a period of inactivity may take **30–60 seconds** while the server spins back up — please be patient on cold starts.

```
Base URL: https://quick-url-frontend-liard.vercel.app

```

---

## ✨ Features

- 🔐 **JWT Authentication** — stateless, secure session handling
- 🛡️ **Spring Security** — protected routes and role-based access
- 👤 **User Registration & Login**
- 🔗 **URL Shortening** — generate compact, shareable short links
- ↪️ **URL Redirection** — fast lookup and redirect to original URL
- 📊 **Click Tracking & Daily Analytics**
- 🗄️ **PostgreSQL Database** integration via Spring Data JPA
- 🌍 **RESTful APIs** for easy frontend/client integration
- 🔒 **Password Encryption** using BCrypt

---

## 🛠️ Tech Stack

| Category | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Security | Spring Security, JWT |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Build Tool | Maven |

---

## 📁 Project Structure

```
src
├── controllers   # REST API endpoints
├── service       # Business logic
├── repository    # Data access layer (JPA repositories)
├── model         # Entity classes
├── dto           # Data Transfer Objects
├── security      # JWT & Spring Security configuration
├── config        # Application-level configuration
└── resources     # application.properties, static assets, etc.
```

---

## 📡 API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/public/register` | Register a new user |
| `POST` | `/api/auth/public/login` | Authenticate and receive a JWT |

### URL Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/urls/shorten` | Create a short URL |
| `GET` | `/api/urls/myurls` | Get all URLs created by the authenticated user |
| `GET` | `/api/urls/analytics/{shortUrl}` | Get click analytics for a short URL |

### Redirection

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/{shortUrl}` | Redirect to the original long URL |

---

## 🔒 Security

- ✅ JWT-based authentication
- ✅ BCrypt password encoding
- ✅ Stateless session management
- ✅ Protected APIs via Spring Security filters
- ✅ CORS configuration for frontend integration

---

## ⚙️ Running Locally

### Prerequisites
- Java 17+
- Maven
- PostgreSQL

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Gauravsharma26/QuickURL-Backend.git
   cd QuickURL-Backend
   ```

2. **Configure environment variables**

   Create a `.env` file or set the following environment variables:

   ```properties
   DB_URL=jdbc:postgresql://localhost:5432/quickurl
   DB_USERNAME=your_db_username
   DB_PASSWORD=your_db_password
   JWT_SECRET=your_jwt_secret_key
   FRONTEND_URL=http://localhost:3000
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. The API will be available at `http://localhost:8080` 🎉

---

## ☁️ Deployment

| Component | Platform |
|---|---|
| Backend | [Render](https://render.com/) |
| Database | Render PostgreSQL |

---

## 👨‍💻 Author

**Gaurav Sharma**

[![GitHub](https://img.shields.io/badge/GitHub-Gauravsharma26-181717?logo=github)](https://github.com/Gauravsharma26)

---

<div align="center">

If you find this project useful, consider giving it a ⭐ on GitHub!

</div>
