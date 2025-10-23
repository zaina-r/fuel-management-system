# Fuel Management System - Setup Guide

This guide will help you set up and run the Fuel Management System on your local machine.

## Prerequisites

Before you begin, ensure you have the following installed:

### Required Software
- **Java 17 or higher** - [Download JDK](https://www.oracle.com/java/technologies/downloads/)
- **Node.js 18.x or higher** - [Download Node.js](https://nodejs.org/)
- **MySQL 8.x** - [Download MySQL](https://dev.mysql.com/downloads/)
- **Git** - [Download Git](https://git-scm.com/downloads)
- **Maven** (included via Maven Wrapper in the project)

### Optional (for Mobile Development)
- **Android Studio** - [Download](https://developer.android.com/studio) (for Android development)
- **Xcode** - (for iOS development, macOS only)
- **Expo CLI** - Will be installed via npm

## Project Structure

```
fuel-management-system/
├── backend/              # Spring Boot backend application
├── frontend/
│   └── reactapp/        # React web application
├── mobileScanner/       # React Native mobile app
└── README.md
```

## Database Setup

### 1. Install MySQL
Install MySQL 8.x on your system following the official documentation.

### 2. Create Database
```sql
CREATE DATABASE fuelmanagement;
```

### 3. Create MySQL User (Optional but Recommended)
```sql
CREATE USER 'fueluser'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON fuelmanagement.* TO 'fueluser'@'localhost';
FLUSH PRIVILEGES;
```

### 4. Import Mock Databases
Navigate to the backend directory and import the SQL files:

```bash
cd backend

# Import DMV mock database
mysql -u root -p fuelmanagement < DMV_mock_database/dmv_db_vehicles.sql
mysql -u root -p fuelmanagement < DMV_mock_database/dmv_db_owner_registrations.sql

# Import fuel station data
mysql -u root -p fuelmanagement < fuel_stations_registered_stations.sql
mysql -u root -p fuelmanagement < fuel_stations_routines.sql
```

## Backend Setup

### 1. Navigate to Backend Directory
```bash
cd backend
```

### 2. Configure Application Properties
Copy the example configuration and update with your settings:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edit `src/main/resources/application.properties` and update:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/fuelmanagement?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_mysql_password

# Mail Configuration (for OTP and password reset)
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
```

**Important Notes:**
- For Gmail, use an [App Password](https://support.google.com/accounts/answer/185833?hl=en) instead of your regular password
- Enable "Less secure app access" if using other email providers

### 3. Build the Backend
```bash
# On Linux/Mac
./mvnw clean install

# On Windows
mvnw.cmd clean install
```

### 4. Run the Backend
```bash
# On Linux/Mac
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

The backend will start on `http://localhost:8080`

### Verify Backend is Running
Open your browser and navigate to:
- `http://localhost:8080` - Should return a response (Spring Boot default page or API endpoint)

## Frontend Setup (Web Application)

### 1. Navigate to Frontend Directory
```bash
cd frontend/reactapp
```

### 2. Install Dependencies
```bash
npm install
```

### 3. Configure API Endpoint (if needed)
Check `src/axios.jsx` and ensure it points to your backend:

```javascript
export const BASE_URL = 'http://localhost:8080';
```

### 4. Start Development Server
```bash
npm run dev
```

The frontend will start on `http://localhost:5173` (or another port if 5173 is busy)

### 5. Access the Application
Open your browser and navigate to `http://localhost:5173`

## Mobile App Setup (React Native with Expo)

### 1. Navigate to Mobile Directory
```bash
cd mobileScanner
```

### 2. Install Dependencies
```bash
npm install
```

### 3. Install Expo CLI Globally (if not already installed)
```bash
npm install -g expo-cli
```

### 4. Configure API Endpoint
Update the API endpoint in your mobile app configuration to point to your backend.
For local testing, you'll need to use your computer's IP address instead of localhost.

Find your IP address:
```bash
# On Linux/Mac
ifconfig | grep "inet "

# On Windows
ipconfig
```

### 5. Start Expo Development Server
```bash
npx expo start
```

### 6. Run on Device/Emulator

#### Option A: Run on Physical Device
1. Install the **Expo Go** app on your phone (available on iOS App Store and Google Play Store)
2. Scan the QR code displayed in your terminal

#### Option B: Run on Android Emulator
1. Install Android Studio and set up an Android emulator
2. Press 'a' in the Expo terminal to run on Android

#### Option C: Run on iOS Simulator (macOS only)
1. Install Xcode
2. Press 'i' in the Expo terminal to run on iOS

## Building for Production

### Backend
```bash
cd backend
./mvnw clean package
# The JAR file will be in target/fuel_management_system-0.0.1-SNAPSHOT.jar
```

Run the production build:
```bash
java -jar target/fuel_management_system-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
cd frontend/reactapp
npm run build
# The production build will be in the dist/ folder
```

Serve the production build:
```bash
npm run preview
```

### Mobile App
```bash
cd mobileScanner

# Build for Android
npx expo build:android

# Build for iOS (macOS only)
npx expo build:ios
```

## Troubleshooting

### Backend Issues

**Problem: "Access denied for user"**
- Check your MySQL credentials in `application.properties`
- Ensure MySQL service is running: `sudo service mysql status`

**Problem: "Port 8080 already in use"**
- Change the port in `application.properties`: `server.port=8081`
- Or stop the process using port 8080

**Problem: "Table doesn't exist"**
- Run the SQL import scripts again
- Check if `spring.jpa.hibernate.ddl-auto=update` is set in `application.properties`

### Frontend Issues

**Problem: "ECONNREFUSED" when calling API**
- Ensure backend is running on `http://localhost:8080`
- Check CORS settings in backend
- Verify API URL in `src/axios.jsx`

**Problem: npm install fails**
- Clear npm cache: `npm cache clean --force`
- Delete `node_modules` and `package-lock.json`, then run `npm install` again

### Mobile App Issues

**Problem: "Network request failed"**
- Use your computer's IP address instead of localhost
- Ensure your phone and computer are on the same WiFi network
- Check if backend allows connections from your IP (firewall settings)

**Problem: Expo won't start**
- Clear Expo cache: `npx expo start --clear`
- Reinstall dependencies: `rm -rf node_modules && npm install`

## Default Users and Testing

After importing the database, you should have some test data. Check the SQL files for default users.

## API Documentation

The backend provides RESTful APIs. Common endpoints:

- **Authentication**: `POST /api/auth/login`
- **Vehicle Registration**: `POST /api/vehicles/register`
- **Fuel Transactions**: `POST /api/fuel/transaction`
- **Station Management**: `GET /api/stations`

For detailed API documentation, consider using Postman or accessing Swagger UI (if configured).

## Development Tips

1. **Hot Reload**: Both frontend and backend support hot reload during development
2. **Database Changes**: Backend uses `spring.jpa.hibernate.ddl-auto=update`, so schema changes are automatic
3. **Logging**: Check backend logs in the console for debugging
4. **React DevTools**: Install React Developer Tools browser extension
5. **Network Inspection**: Use browser DevTools Network tab to debug API calls

## Security Notes

⚠️ **Important**: Before deploying to production:

1. Change all default passwords
2. Use environment variables for sensitive data
3. Enable HTTPS/SSL
4. Review and update CORS settings
5. Implement rate limiting
6. Regular security audits

## Next Steps

1. Read the main [README.md](README.md) for project overview
2. Explore the codebase
3. Set up your development environment
4. Start contributing!

## Getting Help

If you encounter issues:
1. Check the troubleshooting section above
2. Review the issue tracker on GitHub
3. Ask questions in the project discussions
4. Contact the development team

## License

See the LICENSE file for details.

---

**Happy Coding! 🚀**
