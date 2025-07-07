# 🐳 Docker Setup

## Overview
This project uses a **single, optimized Dockerfile** for all environments.

## Files

### `Dockerfile` - Main Production Build
- **Multi-stage build** (React + Spring Boot)
- **Production optimized** with JRE-only runtime
- **Used by**: Production, CI/CD, local Docker Compose

### `docker-compose.yml`
- Uses main Dockerfile
- Full application (frontend + backend)

### `dev.sh` - Hot Reload Development
- Backend runs in Docker
- Frontend runs locally with hot reload
- Best development experience

## Usage

```bash

# Development (hot reload)
sh ./dev.sh

```