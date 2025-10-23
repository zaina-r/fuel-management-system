# System Architecture

This document provides an overview of the Fuel Management System architecture, components, and data flow.

## Table of Contents
- [System Overview](#system-overview)
- [Technology Stack](#technology-stack)
- [Architecture Diagram](#architecture-diagram)
- [Components](#components)
- [Database Schema](#database-schema)
- [API Design](#api-design)
- [Authentication Flow](#authentication-flow)
- [Data Flow](#data-flow)
- [Security Considerations](#security-considerations)

## System Overview

The Fuel Management System is a full-stack application designed to manage fuel allocation and distribution through QR code-based tracking. The system consists of three main components:

1. **Backend API** - RESTful API built with Spring Boot
2. **Web Frontend** - React-based web application for vehicle owners and admins
3. **Mobile App** - React Native mobile application for fuel station workers

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.4.0
- **Language**: Java 17
- **ORM**: Hibernate 6.x
- **Database**: MySQL 8.x
- **Authentication**: JWT (JSON Web Tokens)
- **Build Tool**: Maven
- **Security**: Spring Security

### Frontend (Web)
- **Framework**: React 18.x
- **Build Tool**: Vite
- **Styling**: Tailwind CSS
- **HTTP Client**: Axios
- **Routing**: React Router DOM
- **State Management**: React Hooks (useState, useEffect)

### Mobile
- **Framework**: React Native 0.76.x
- **Platform**: Expo
- **Navigation**: React Navigation
- **QR Scanner**: expo-camera, expo-barcode-scanner

## Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                        Clients                               │
├─────────────────────────┬───────────────────────────────────┤
│                         │                                    │
│   Web Application       │      Mobile Application           │
│   (React + Vite)        │      (React Native + Expo)       │
│                         │                                    │
│   - Vehicle Owners      │      - Fuel Station Workers       │
│   - Admin Panel         │      - QR Code Scanner            │
│                         │      - Fuel Transaction           │
└────────────┬────────────┴────────────────┬──────────────────┘
             │                              │
             │         HTTPS/REST API       │
             │                              │
             └──────────────┬───────────────┘
                            │
             ┌──────────────▼──────────────┐
             │      Backend Server         │
             │    (Spring Boot + Java)     │
             │                             │
             │  ┌──────────────────────┐  │
             │  │  Controllers         │  │
             │  │  - Auth Controller   │  │
             │  │  - Vehicle Controller│  │
             │  │  - Station Controller│  │
             │  │  - Fuel Controller   │  │
             │  └──────────┬───────────┘  │
             │             │               │
             │  ┌──────────▼───────────┐  │
             │  │  Services            │  │
             │  │  - Business Logic    │  │
             │  │  - Validation        │  │
             │  │  - QR Generation     │  │
             │  └──────────┬───────────┘  │
             │             │               │
             │  ┌──────────▼───────────┐  │
             │  │  Repositories        │  │
             │  │  (Spring Data JPA)   │  │
             │  └──────────┬───────────┘  │
             │             │               │
             │  ┌──────────▼───────────┐  │
             │  │  Security Layer      │  │
             │  │  - JWT Auth          │  │
             │  │  - Role-Based Access │  │
             │  └──────────────────────┘  │
             └─────────────┬───────────────┘
                           │
             ┌─────────────▼───────────────┐
             │      MySQL Database         │
             │                             │
             │  Tables:                    │
             │  - user_accounts            │
             │  - registered_vehicles      │
             │  - stations                 │
             │  - fuel_transactions        │
             │  - fuel_allocations         │
             │  - DMV mock databases       │
             └─────────────────────────────┘
```

## Components

### 1. Backend (Spring Boot)

#### Package Structure
```
org.example.fuel_management_system
├── controller/          # REST API endpoints
├── service/            # Business logic
├── repository/         # Data access layer
├── model/              # Entity classes
├── DTO/                # Data Transfer Objects
├── security/           # Security configuration
├── exception/          # Custom exceptions
└── enumpackage/        # Enumerations
```

#### Key Components

**Controllers**
- `AuthController` - User authentication and registration
- `VehicleController` - Vehicle registration and management
- `StationController` - Fuel station operations
- `FuelController` - Fuel transactions and allocations
- `AdminController` - Admin operations

**Services**
- `AuthService` - Authentication and JWT management
- `VehicleService` - Vehicle verification and QR generation
- `StationService` - Station registration and management
- `FuelService` - Fuel allocation and transaction processing
- `EmailService` - OTP and notification emails

**Repositories** (JPA)
- Interface-based repositories extending `JpaRepository`
- Custom query methods
- Database operations abstraction

### 2. Web Frontend (React)

#### Directory Structure
```
src/
├── components/         # Reusable UI components
├── pages/              # Page components
├── AdminPages/         # Admin-specific pages
├── apiservice/         # API service layer
├── Assets/             # Images, icons, etc.
├── animation/          # Animation utilities
└── responseDisplay/    # Response handling components
```

#### Key Features
- **Routing** - Client-side routing with React Router
- **State Management** - Local state with hooks
- **API Integration** - Axios for HTTP requests
- **Responsive Design** - Tailwind CSS for mobile-first design

### 3. Mobile App (React Native)

#### Directory Structure
```
app/
├── scanner/           # QR code scanning screens
├── images/            # Image assets
├── index.tsx          # Login/main screen
├── result.tsx         # Scan result display
└── _layout.tsx        # Navigation layout
```

#### Key Features
- **QR Scanning** - Camera-based QR code scanning
- **Manual Entry** - Alternative QR code input
- **Offline Capability** - Basic offline functionality
- **Cross-Platform** - iOS and Android support

## Database Schema

### Core Tables

**user_accounts**
- id (PK)
- username
- email
- password (hashed)
- role (VEHICLE_OWNER, STATION_OWNER, ADMIN)
- profile information
- timestamps

**registered_vehicles**
- id (PK)
- user_id (FK)
- vehicle_number
- chassis_number
- vehicle_type
- fuel_type
- qr_code
- verification_status
- timestamps

**stations**
- id (PK)
- user_id (FK)
- license_number
- station_name
- address
- verification_status
- station_code (unique login code)
- timestamps

**fuel_allocations**
- id (PK)
- vehicle_id (FK)
- weekly_quota
- remaining_quota
- last_reset_date
- timestamps

**fuel_transactions**
- id (PK)
- vehicle_id (FK)
- station_id (FK)
- amount
- transaction_date
- timestamps

**DMV Mock Databases**
- dmv_vehicles - Mock vehicle registry
- dmv_owner_registrations - Mock owner data

### Relationships
- One User → Many Vehicles
- One User → One Station (for station owners)
- One Vehicle → Many Transactions
- One Station → Many Transactions
- One Vehicle → One Fuel Allocation

## API Design

### REST API Endpoints

**Authentication**
```
POST   /api/auth/register        - User registration
POST   /api/auth/login           - User login
POST   /api/auth/logout          - User logout
POST   /api/auth/refresh-token   - Refresh JWT token
POST   /api/auth/forgot-password - Password reset request
POST   /api/auth/reset-password  - Password reset confirmation
```

**Vehicles**
```
GET    /api/vehicles              - List user's vehicles
POST   /api/vehicles/register     - Register new vehicle
GET    /api/vehicles/{id}         - Get vehicle details
PUT    /api/vehicles/{id}         - Update vehicle
DELETE /api/vehicles/{id}         - Delete vehicle
GET    /api/vehicles/{id}/qr      - Get QR code
GET    /api/vehicles/{id}/quota   - Get fuel quota
```

**Stations**
```
GET    /api/stations              - List all stations
POST   /api/stations/register     - Register new station
GET    /api/stations/{id}         - Get station details
PUT    /api/stations/{id}         - Update station
POST   /api/stations/verify-qr    - Verify vehicle QR code
```

**Fuel Transactions**
```
POST   /api/fuel/transaction      - Create fuel transaction
GET    /api/fuel/history          - Get transaction history
GET    /api/fuel/allocation/{id}  - Get fuel allocation
```

**Admin**
```
GET    /api/admin/users           - List all users
GET    /api/admin/stations        - List all stations
PUT    /api/admin/station/{id}    - Update station status
GET    /api/admin/vehicles        - List all vehicles
GET    /api/admin/reports         - Generate reports
```

### API Response Format

**Success Response**
```json
{
  "status": "success",
  "data": {
    // response data
  },
  "message": "Operation successful"
}
```

**Error Response**
```json
{
  "status": "error",
  "error": {
    "code": "ERROR_CODE",
    "message": "Error description"
  }
}
```

## Authentication Flow

### 1. Registration
```
User → POST /api/auth/register
     → Validate input
     → Hash password
     → Create user account
     → Return success
```

### 2. Login
```
User → POST /api/auth/login
     → Validate credentials
     → Generate JWT token
     → Return token + user info
```

### 3. Authenticated Requests
```
Client → Request with JWT in header
       → Validate token
       → Check permissions
       → Process request
       → Return response
```

### 4. Token Refresh
```
Client → POST /api/auth/refresh-token
       → Validate refresh token
       → Generate new access token
       → Return new token
```

## Data Flow

### Vehicle Registration Flow
```
1. User enters vehicle details
2. Frontend validates input
3. POST /api/vehicles/register
4. Backend validates against DMV database
5. If valid:
   - Create vehicle record
   - Generate QR code
   - Create fuel allocation
6. Return vehicle details + QR code
```

### Fuel Transaction Flow
```
1. Station worker scans QR code
2. Mobile app decodes vehicle ID
3. POST /api/stations/verify-qr
4. Backend:
   - Validates vehicle
   - Checks fuel quota
   - Returns vehicle details
5. Worker enters fuel amount
6. POST /api/fuel/transaction
7. Backend:
   - Validates quota
   - Records transaction
   - Updates remaining quota
8. Return confirmation
```

### Weekly Quota Reset Flow
```
1. Cron job runs weekly
2. For each vehicle:
   - Reset quota to max
   - Update last_reset_date
3. Log reset operations
```

## Security Considerations

### 1. Authentication & Authorization
- JWT tokens with expiration
- Role-based access control (RBAC)
- Password hashing with BCrypt
- Secure session management

### 2. Data Protection
- HTTPS for all communications
- Input validation and sanitization
- SQL injection prevention (JPA)
- XSS protection

### 3. API Security
- Rate limiting
- CORS configuration
- Request validation
- Error handling without information leakage

### 4. Database Security
- Principle of least privilege
- Connection pooling
- Prepared statements
- Regular backups

### 5. QR Code Security
- Unique, non-predictable codes
- Time-based validation (optional)
- One-time use tokens (optional)

## Scalability Considerations

### Horizontal Scaling
- Stateless backend design
- Load balancer support
- Session management via JWT

### Database Optimization
- Indexing on frequently queried columns
- Query optimization
- Connection pooling
- Caching strategy

### Performance
- Lazy loading for entities
- Pagination for large datasets
- CDN for static assets
- API response caching

## Monitoring & Logging

### Application Logs
- Request/response logging
- Error tracking
- Performance metrics
- Security events

### Database Logs
- Query performance
- Connection statistics
- Transaction logs

### Monitoring Tools (Recommended)
- Spring Boot Actuator
- Prometheus + Grafana
- ELK Stack (Elasticsearch, Logstash, Kibana)
- Application Performance Monitoring (APM)

## Deployment Architecture

### Development
```
Local Machine
├── MySQL (Docker)
├── Backend (./mvnw spring-boot:run)
├── Frontend (npm run dev)
└── Mobile (npx expo start)
```

### Production (Recommended)
```
Cloud Provider (AWS, Azure, GCP)
├── Load Balancer
├── Application Servers (multiple instances)
│   └── Spring Boot (JAR)
├── Database Server
│   └── MySQL (RDS, Cloud SQL, etc.)
├── CDN
│   └── Static Assets
└── Monitoring & Logging
```

## Future Enhancements

1. **Real-time Updates** - WebSocket integration
2. **Microservices** - Service decomposition
3. **Caching** - Redis integration
4. **Message Queue** - RabbitMQ/Kafka for async processing
5. **Analytics** - Advanced reporting and analytics
6. **Mobile Offline Support** - Better offline capabilities
7. **Push Notifications** - Real-time alerts
8. **API Versioning** - Support multiple API versions

---

For more information, refer to:
- [SETUP.md](SETUP.md) - Setup instructions
- [CONTRIBUTING.md](CONTRIBUTING.md) - Contribution guidelines
- [SECURITY.md](SECURITY.md) - Security best practices
