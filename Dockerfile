# Multi-stage build for React + Spring Boot application

# Stage 1: Build React frontend
FROM node:18-alpine AS frontend-builder

# Set working directory
WORKDIR /app/frontend

# Copy package files first for better caching
COPY frontend/package*.json ./

# Install dependencies
RUN npm install

# Copy all frontend source files
COPY frontend/ ./

# Build React app
RUN npm run build

# Stage 2: Build Spring Boot application
FROM maven:3.9.10-eclipse-temurin-17 AS backend-builder

# Set working directory
WORKDIR /app

# Copy Maven files
COPY pom.xml ./
COPY mvnw ./
COPY .mvn/ ./.mvn/

# Copy source code
COPY src/ ./src/

# Copy built React files from frontend stage
COPY --from=frontend-builder /app/frontend/build/ ./src/main/resources/static/

# Build Spring Boot application
RUN ./mvnw clean package -DskipTests

# Stage 3: Runtime container
FROM eclipse-temurin:17-jre

# Install curl for health check
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Create app user
RUN addgroup --gid 1001 appgroup && \
    adduser --uid 1001 --gid 1001 --disabled-password --gecos '' appuser

# Set working directory
WORKDIR /app

# Copy built JAR from backend stage
COPY --from=backend-builder /app/target/*.jar app.jar

# Change ownership to app user
RUN chown -R appuser:appgroup /app

# Switch to app user
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/api/hello || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 