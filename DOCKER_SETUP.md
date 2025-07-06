# 🐳 Docker Setup

## Overview
This project uses a **single, optimized Dockerfile** for all environments.

## Files

### `Dockerfile` - Main Production Build
- **Multi-stage build** (React + Spring Boot)
- **Production optimized** with JRE-only runtime
- **Used by**: Production, CI/CD, local Docker Compose

### `docker-compose.yml` - Production
- Uses main Dockerfile
- Full application (frontend + backend)

### `docker-compose.dev.yml` - Development
- Uses main Dockerfile
- Backend only (for `dev.sh` script)

### `dev.sh` - Hot Reload Development
- Backend runs in Docker
- Frontend runs locally with hot reload
- Best development experience

## Usage

```bash
# Production build
docker build -t myapp .

# Production run
docker compose up

# Development (hot reload)
sh ./dev.sh

# Backend-only development
docker compose -f docker-compose.dev.yml up
```

## Why Single Dockerfile?
- ✅ **Simpler maintenance**
- ✅ **Consistent builds** across environments
- ✅ **Faster builds** with proper caching
- ✅ **Less confusion** for developers 