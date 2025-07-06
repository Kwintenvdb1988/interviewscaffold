#!/bin/bash

# Development script for running backend in Docker and frontend locally

echo "🚀 Starting development environment..."

# Start the backend in Docker (includes frontend build)
echo "📦 Starting Spring Boot backend in Docker..."
docker-compose -f docker-compose.dev.yml up -d

# Wait for backend to be ready
echo "⏳ Waiting for backend to be ready..."
until curl -f http://localhost:8080/api/hello > /dev/null 2>&1; do
    echo "   Backend not ready yet, waiting..."
    sleep 2
done
echo "✅ Backend is ready!"

# Start the frontend with hot reload (for development)
echo "⚛️  Starting React frontend with hot reload..."
echo "   Frontend will be available at http://localhost:3000"
echo "   Backend API will be available at http://localhost:8080"
echo "   Production build (with embedded frontend) at http://localhost:8080"
echo ""
cd frontend
npm start 