# Quick Reference Guide

This is a quick reference guide for common commands and operations in the Fuel Management System.

## 📋 Table of Contents
- [Prerequisites Check](#prerequisites-check)
- [Database Commands](#database-commands)
- [Backend Commands](#backend-commands)
- [Frontend Commands](#frontend-commands)
- [Mobile App Commands](#mobile-app-commands)
- [Docker Commands](#docker-commands)
- [Git Workflow](#git-workflow)
- [Common Issues](#common-issues)

## Prerequisites Check

### Check Installed Versions
```bash
# Java version (should be 17+)
java -version

# Node.js version (should be 18+)
node --version
npm --version

# MySQL version (should be 8+)
mysql --version

# Docker version
docker --version
docker compose version

# Maven (via wrapper)
cd backend
./mvnw --version
```

## Database Commands

### Using MySQL Command Line
```bash
# Connect to MySQL
mysql -u root -p

# Show all databases
SHOW DATABASES;

# Use fuel management database
USE fuelmanagement;

# Show all tables
SHOW TABLES;

# View table structure
DESCRIBE registered_vehicles;

# Count records in a table
SELECT COUNT(*) FROM registered_vehicles;

# Exit MySQL
exit;
```

### Import SQL Files
```bash
cd backend

# Import DMV database
mysql -u root -p fuelmanagement < DMV_mock_database/dmv_db_vehicles.sql
mysql -u root -p fuelmanagement < DMV_mock_database/dmv_db_owner_registrations.sql

# Import fuel station data
mysql -u root -p fuelmanagement < fuel_stations_registered_stations.sql
mysql -u root -p fuelmanagement < fuel_stations_routines.sql
```

### Backup and Restore
```bash
# Backup database
mysqldump -u root -p fuelmanagement > backup_$(date +%Y%m%d).sql

# Restore database
mysql -u root -p fuelmanagement < backup_20250123.sql
```

## Backend Commands

### Maven Wrapper Commands
```bash
cd backend

# Clean build
./mvnw clean

# Compile only
./mvnw compile

# Run tests
./mvnw test

# Skip tests and build
./mvnw clean install -DskipTests

# Create JAR file
./mvnw package

# Run Spring Boot application
./mvnw spring-boot:run

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Running Built JAR
```bash
cd backend/target
java -jar fuel_management_system-0.0.1-SNAPSHOT.jar
```

### View Logs
```bash
# Backend logs are in console by default
# To save to file:
./mvnw spring-boot:run > backend.log 2>&1
```

## Frontend Commands

### npm Commands
```bash
cd frontend/reactapp

# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview

# Run linter
npm run lint

# Clean node_modules and reinstall
rm -rf node_modules package-lock.json
npm install
```

### Vite Specific
```bash
# Start with specific port
npm run dev -- --port 3000

# Start with host access (for mobile testing)
npm run dev -- --host

# Clear cache and start
rm -rf node_modules/.vite
npm run dev
```

## Mobile App Commands

### Expo Commands
```bash
cd mobileScanner

# Install dependencies
npm install

# Start Expo
npx expo start

# Start with cache cleared
npx expo start --clear

# Start in specific mode
npx expo start --ios
npx expo start --android
npx expo start --web

# Build for production
npx expo build:android
npx expo build:ios
```

### Troubleshooting Mobile
```bash
# Reset project
npm run reset-project

# Clear watchman (if installed)
watchman watch-del-all

# Fix peer dependencies
npm install --legacy-peer-deps
```

## Docker Commands

### Basic Docker Compose
```bash
# Start all services
docker compose up -d

# Stop all services
docker compose stop

# Start services
docker compose start

# Restart services
docker compose restart

# Stop and remove containers
docker compose down

# Stop and remove with volumes (deletes data!)
docker compose down -v

# View logs
docker compose logs -f

# View specific service logs
docker compose logs -f mysql
```

### Docker MySQL Commands
```bash
# Execute MySQL in container
docker exec -it fuel-management-mysql mysql -u root -p

# Import SQL file
docker exec -i fuel-management-mysql mysql -u root -prootpassword fuelmanagement < file.sql

# Backup database
docker exec fuel-management-mysql mysqldump -u root -prootpassword fuelmanagement > backup.sql

# Copy file to container
docker cp localfile.sql fuel-management-mysql:/tmp/

# Check container status
docker compose ps

# View container resource usage
docker stats fuel-management-mysql
```

## Git Workflow

### Basic Git Commands
```bash
# Clone repository
git clone https://github.com/zaina-r/fuel-management-system.git

# Check status
git status

# View changes
git diff

# Add files
git add .
git add specific-file.txt

# Commit changes
git commit -m "Your message"

# Push to remote
git push origin branch-name

# Pull latest changes
git pull origin main
```

### Branching
```bash
# Create new branch
git checkout -b feature/new-feature

# Switch branch
git checkout main

# List all branches
git branch -a

# Delete local branch
git branch -d feature/old-feature

# Update from main
git checkout feature/my-feature
git merge main
```

### Useful Git Commands
```bash
# Undo last commit (keep changes)
git reset --soft HEAD~1

# Discard local changes
git checkout -- filename

# View commit history
git log --oneline

# Show specific commit
git show commit-hash

# Stash changes
git stash
git stash pop
```

## Common Issues

### Port Already in Use

**Backend (8080):**
```bash
# Find process
lsof -i :8080
# or
netstat -ano | findstr :8080

# Kill process
kill -9 <PID>
```

**Frontend (5173):**
```bash
# Find process
lsof -i :5173

# Kill process
kill -9 <PID>
```

### Clear Caches

**Backend:**
```bash
cd backend
./mvnw clean
rm -rf target/
```

**Frontend:**
```bash
cd frontend/reactapp
rm -rf node_modules/.vite
rm -rf dist/
```

**Mobile:**
```bash
cd mobileScanner
rm -rf node_modules/.expo
npx expo start --clear
```

### Reset Everything

**Complete Reset:**
```bash
# Stop all services
docker compose down -v

# Clean backend
cd backend
./mvnw clean
rm -rf target/

# Clean frontend
cd ../frontend/reactapp
rm -rf node_modules dist/
npm install

# Clean mobile
cd ../../mobileScanner
rm -rf node_modules
npm install

# Start fresh
docker compose up -d
```

## Quick Start Sequence

### For First Time Setup
```bash
# 1. Clone and navigate
git clone https://github.com/zaina-r/fuel-management-system.git
cd fuel-management-system

# 2. Start database
docker compose up -d

# 3. Configure backend
cd backend
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit application.properties with your settings

# 4. Start backend
./mvnw spring-boot:run

# 5. In new terminal, start frontend
cd frontend/reactapp
npm install
npm run dev

# 6. Access application
# Frontend: http://localhost:5173
# Backend: http://localhost:8080
# phpMyAdmin: http://localhost:8081
```

### For Daily Development
```bash
# Terminal 1 - Backend
cd backend
./mvnw spring-boot:run

# Terminal 2 - Frontend
cd frontend/reactapp
npm run dev

# Terminal 3 - Mobile (optional)
cd mobileScanner
npx expo start
```

## Environment Variables

### Check Current Environment
```bash
# Backend
cd backend
grep -v '^#' src/main/resources/application.properties | grep -v '^$'

# Frontend
cd frontend/reactapp
cat .env
```

### Set Environment Variables
```bash
# Linux/Mac
export MYSQL_PASSWORD=yourpassword

# Windows Command Prompt
set MYSQL_PASSWORD=yourpassword

# Windows PowerShell
$env:MYSQL_PASSWORD="yourpassword"
```

## Performance Tips

### Speed Up Backend Startup
```bash
# Skip tests
./mvnw spring-boot:run -DskipTests

# Use dev profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Speed Up Frontend Build
```bash
# Use development build
npm run dev

# Disable source maps
npm run build -- --no-sourcemap
```

## Useful URLs

When everything is running:

- **Frontend**: http://localhost:5173
- **Backend**: http://localhost:8080
- **Backend Health**: http://localhost:8080/actuator/health (if actuator enabled)
- **phpMyAdmin**: http://localhost:8081
- **API Base**: http://localhost:8080/api

## Need More Help?

- See [SETUP.md](SETUP.md) for detailed setup instructions
- See [DOCKER.md](DOCKER.md) for Docker-specific help
- See [CONTRIBUTING.md](CONTRIBUTING.md) for contribution guidelines
- Check GitHub Issues for known problems
- Read the main [README.md](README.md) for project overview

---

**Keep this guide handy for quick reference! 📚**
