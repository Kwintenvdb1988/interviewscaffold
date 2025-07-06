# interviewscaffold

A full-stack application scaffold with **Spring Boot 3.5.3** (Java 17) backend and **React 19 + TypeScript** frontend, containerized with Docker.

---

## 🚀 Features
- **Spring Boot** REST API backend
- **React** frontend (modern, beautiful UI)
- **Single-command build** (Maven automates React build)
- **Dockerized**: Multi-stage Dockerfile for production, Compose for local/dev
- **Hot reload** support for development

---

## 🏗️ Build & Run (Locally)

### 1. Build & Run Everything (auto-builds frontend)
```bash
./mvnw spring-boot:run
```
- Access app: [http://localhost:8080](http://localhost:8080)

### 2. Build Frontend Only
```bash
cd frontend
npm run build:spring
```

---

## 🐳 Docker Usage

### 1. Build Docker Image
```bash
docker build -t interviewscaffold .
```

### 2. Run with Docker Compose
```bash
docker compose up
```
- Access app: [http://localhost:8080](http://localhost:8080)

---

## 🗂️ Project Structure
```
backend/
├── src/main/java/...         # Spring Boot backend
├── src/main/resources/static # React build output (served by Spring Boot)
├── frontend/                 # React app source
├── Dockerfile                # Multi-stage Docker build
├── docker-compose.yml        # Compose for orchestration
└── ...
```

---

## ✨ Development
- Edit React code in `frontend/`, Java code in `src/main/java/`
- Use `dev.sh` for hot reload development (backend in Docker, frontend locally)
- Use `docker compose up` for full application in Docker

---

## 📦 Requirements
- Java 17+
- Node.js 18+ (for local dev)
- Docker (for containerized usage)

---

## 📝 License
MIT (add your license if needed) 