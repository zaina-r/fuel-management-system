# Frequently Asked Questions (FAQ)

Common questions and answers about the Fuel Management System.

## Table of Contents
- [General Questions](#general-questions)
- [Installation & Setup](#installation--setup)
- [Database](#database)
- [Backend](#backend)
- [Frontend](#frontend)
- [Mobile App](#mobile-app)
- [Development](#development)
- [Troubleshooting](#troubleshooting)

## General Questions

### What is the Fuel Management System?
The Fuel Management System is a comprehensive application for managing fuel allocation and distribution. It uses QR code-based tracking to manage weekly fuel quotas for vehicles, with a web interface for vehicle owners and admins, plus a mobile app for fuel station workers.

### Who can use this system?
The system has three types of users:
1. **Vehicle Owners** - Register vehicles and manage fuel quotas
2. **Fuel Station Owners** - Register stations and manage fuel distribution
3. **Admins** - Monitor and manage all system operations

### Is this open source?
Yes! This project is licensed under the MIT License. You can use, modify, and distribute it freely.

### What are the main features?
- Vehicle registration with DMV verification
- QR code generation for vehicles
- Weekly fuel quota management
- Mobile QR scanning for fuel stations
- Real-time transaction tracking
- Admin dashboard for monitoring
- Email notifications and OTP verification

## Installation & Setup

### What do I need to install?
Required:
- Java 17 or higher
- Node.js 18 or higher
- MySQL 8.x
- Git

Optional:
- Docker (for easier database setup)
- Android Studio or Xcode (for mobile development)

Run `./check-prerequisites.sh` to verify your installation.

### How long does setup take?
- With Docker: ~15-20 minutes
- Manual setup: ~30-45 minutes

First-time setup includes downloading dependencies, which takes most of the time.

### Can I use Docker for everything?
Currently, Docker is provided for the database only. You can extend the `docker-compose.yml` to include the backend and frontend if desired.

### Do I need to set up all three components?
No. You can set up only what you need:
- Backend + Frontend for web-only deployment
- Backend + Mobile for station-only deployment
- All three for full functionality

### Where do I start?
1. Read the main [README.md](README.md)
2. Run `./check-prerequisites.sh`
3. Follow [SETUP.md](SETUP.md) or [DOCKER.md](DOCKER.md)
4. Check [QUICKREF.md](QUICKREF.md) for commands

## Database

### Can I use a database other than MySQL?
The system is designed for MySQL, but you can adapt it to other databases (PostgreSQL, MariaDB, etc.) by:
1. Changing the database driver in `pom.xml`
2. Updating the connection URL in `application.properties`
3. Adjusting any MySQL-specific SQL syntax

### How do I reset the database?
```bash
# Using Docker
docker compose down -v
docker compose up -d

# Manual
mysql -u root -p
DROP DATABASE fuelmanagement;
CREATE DATABASE fuelmanagement;
# Then re-import SQL files
```

### What are the DMV mock databases for?
The DMV (Department of Motor Vehicles) mock databases simulate a government vehicle registry. They're used to verify vehicle and owner information during registration. In a real deployment, you would integrate with actual government APIs.

### How often is the fuel quota reset?
Fuel quotas are reset weekly. The exact timing can be configured in the backend scheduler.

### Can I change the quota amounts?
Yes. Quota amounts are configurable in the backend code or can be made dynamic through admin settings.

## Backend

### What port does the backend run on?
Default is 8080. You can change it in `application.properties`:
```properties
server.port=8081
```

### How do I configure email for OTP?
Edit `application.properties`:
```properties
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
```

For Gmail, you need to create an [App Password](https://support.google.com/accounts/answer/185833).

### What is the JWT secret?
JWT (JSON Web Token) secret is used to sign authentication tokens. Set a strong secret in production. It should be at least 256 bits (32 characters).

### How do I add new API endpoints?
1. Create a controller method in the appropriate controller
2. Add service layer logic
3. Update repository if needed
4. Document the API
5. Test with Postman

### Can I disable security for testing?
Not recommended, but you can modify `SecurityConfig` to permit all requests for specific endpoints. Always re-enable for production.

## Frontend

### What port does the frontend run on?
Vite defaults to port 5173. You can change it:
```bash
npm run dev -- --port 3000
```

### How do I build for production?
```bash
npm run build
```
This creates optimized files in the `dist/` folder.

### Where do I configure the API URL?
In `src/axios.jsx`, update the `BASE_URL`:
```javascript
export const BASE_URL = 'http://localhost:8080';
```

### How do I add new pages?
1. Create a component in `src/pages/`
2. Add route in `App.jsx`
3. Update navigation if needed
4. Style with Tailwind CSS

### Can I use a different styling framework?
Yes, but Tailwind CSS is integrated. To use something else:
1. Install the new framework
2. Update `tailwind.config.js` or remove it
3. Update component styles

## Mobile App

### Which platforms are supported?
Both iOS and Android are supported through React Native and Expo.

### Why can't I connect to the backend from mobile?
Mobile devices can't use `localhost`. Use your computer's IP address instead:
```javascript
const API_URL = 'http://192.168.1.100:8080';
```

Find your IP:
- Windows: `ipconfig`
- Mac/Linux: `ifconfig`

### Do I need Android Studio or Xcode?
Not required for testing:
- Use Expo Go app on your phone
- Or use Expo's web-based testing

Required for building production apps.

### How do I test without a physical device?
1. Use Android Emulator (from Android Studio)
2. Use iOS Simulator (from Xcode, macOS only)
3. Use Expo's web interface

### Can the mobile app work offline?
Currently, the app requires an internet connection. Offline support can be added by:
1. Implementing local storage
2. Syncing data when online
3. Handling offline transactions

## Development

### How do I contribute?
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

See [CONTRIBUTING.md](CONTRIBUTING.md) for details.

### What coding standards should I follow?
- Backend: Google Java Style Guide
- Frontend: Airbnb React Style Guide
- See [CONTRIBUTING.md](CONTRIBUTING.md) for specifics

### How do I run tests?
```bash
# Backend
cd backend
./mvnw test

# Frontend (if tests exist)
cd frontend/reactapp
npm test
```

### How do I debug the backend?
1. Run with debug flag:
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
   ```
2. Attach your IDE debugger to port 5005
3. Set breakpoints and debug

### How do I debug the frontend?
1. Use React DevTools browser extension
2. Use browser DevTools Console and Network tabs
3. Add `console.log()` statements
4. Use VS Code debugger

## Troubleshooting

### Port already in use
```bash
# Find process
lsof -i :8080

# Kill process
kill -9 <PID>
```

### MySQL connection refused
1. Check if MySQL is running: `sudo service mysql status`
2. Verify credentials in `application.properties`
3. Check if database exists: `SHOW DATABASES;`

### Cannot connect to backend from frontend
1. Check if backend is running: `http://localhost:8080`
2. Verify API URL in frontend code
3. Check browser console for CORS errors
4. Verify backend CORS configuration

### npm install fails
```bash
# Clear cache
npm cache clean --force

# Remove and reinstall
rm -rf node_modules package-lock.json
npm install
```

### Maven build fails
```bash
# Clean and rebuild
./mvnw clean install

# Skip tests if they're failing
./mvnw clean install -DskipTests
```

### QR code not scanning
1. Check camera permissions
2. Ensure good lighting
3. Try manual entry as alternative
4. Verify QR code generation is working

### "Table doesn't exist" error
Import the SQL files again:
```bash
cd backend
mysql -u root -p fuelmanagement < fuel_stations_routines.sql
```

### "Access denied" for MySQL
1. Check username/password in `application.properties`
2. Verify user has proper permissions
3. Try connecting with MySQL client directly

### Frontend shows blank page
1. Check browser console for errors
2. Verify backend is running
3. Check API endpoint configuration
4. Clear browser cache

### Mobile app won't start
```bash
# Clear cache
npx expo start --clear

# Reinstall dependencies
rm -rf node_modules
npm install
```

### JWT token expired
Tokens expire after a set time. Either:
1. Log in again
2. Implement token refresh
3. Increase token expiration time (not recommended for production)

## Performance Questions

### How many users can the system handle?
This depends on your infrastructure. The stateless design allows for horizontal scaling. Performance testing is recommended for your specific use case.

### How do I improve performance?
1. Enable database query caching
2. Implement Redis for session management
3. Use CDN for static assets
4. Optimize database indexes
5. Implement API response caching
6. Use connection pooling

### Database is slow
1. Add indexes to frequently queried columns
2. Optimize queries
3. Enable query caching
4. Consider read replicas
5. Monitor slow query log

## Security Questions

### Is the system secure?
The system implements:
- JWT authentication
- Password hashing (BCrypt)
- Input validation
- SQL injection prevention
- CORS protection

For production, follow [SECURITY.md](SECURITY.md) recommendations.

### How do I report a security issue?
See [SECURITY.md](SECURITY.md) for responsible disclosure process.

### Should I use this in production as-is?
The system provides a solid foundation, but before production:
1. Security audit
2. Penetration testing
3. HTTPS/SSL setup
4. Rate limiting implementation
5. Proper monitoring and logging
6. Follow all items in [SECURITY.md](SECURITY.md)

## Deployment Questions

### How do I deploy to production?
1. Build backend JAR: `./mvnw package`
2. Build frontend: `npm run build`
3. Deploy to your server (AWS, Azure, GCP, etc.)
4. Set up MySQL database
5. Configure HTTPS/SSL
6. Set environment variables
7. Start the application

### Can I use cloud services?
Yes! The system works with:
- AWS (EC2, RDS, S3, etc.)
- Google Cloud Platform
- Microsoft Azure
- Heroku
- DigitalOcean
- Any VPS provider

### Do I need a domain name?
Not required for testing, but recommended for production. You'll need it for:
- Professional appearance
- SSL certificates
- Email configuration

## Still Have Questions?

1. Check the documentation:
   - [SETUP.md](SETUP.md)
   - [ARCHITECTURE.md](ARCHITECTURE.md)
   - [CONTRIBUTING.md](CONTRIBUTING.md)
   - [SECURITY.md](SECURITY.md)

2. Search [GitHub Issues](https://github.com/zaina-r/fuel-management-system/issues)

3. Open a new issue with the `question` label

4. Join discussions in the repository

---

**Don't see your question?** [Open an issue](https://github.com/zaina-r/fuel-management-system/issues/new) and we'll add it to this FAQ!
