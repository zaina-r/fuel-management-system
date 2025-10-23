# Quick Start with Docker

This guide helps you get the database up and running quickly using Docker.

## Prerequisites

- [Docker](https://docs.docker.com/get-docker/) installed
- [Docker Compose](https://docs.docker.com/compose/install/) installed

## Quick Start

### 1. Start the Database

From the root directory of the project, run:

```bash
docker-compose up -d
```

This will:
- Start MySQL database on port 3306
- Start phpMyAdmin on port 8081 (optional GUI for database management)
- Automatically import the SQL scripts

### 2. Verify Database is Running

```bash
docker-compose ps
```

You should see both `fuel-management-mysql` and `fuel-management-phpmyadmin` running.

### 3. Access phpMyAdmin (Optional)

Open your browser and navigate to:
- URL: `http://localhost:8081`
- Server: `mysql`
- Username: `root`
- Password: `rootpassword`

Or use the regular user:
- Username: `fueluser`
- Password: `fuelpassword`

### 4. Configure Backend

Update your `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fuelmanagement
spring.datasource.username=fueluser
spring.datasource.password=fuelpassword
```

Or use root:
```properties
spring.datasource.username=root
spring.datasource.password=rootpassword
```

### 5. Start Backend and Frontend

Follow the regular setup instructions in [SETUP.md](SETUP.md) to start the backend and frontend applications.

## Managing Docker Containers

### View Logs

```bash
# All services
docker-compose logs -f

# MySQL only
docker-compose logs -f mysql

# phpMyAdmin only
docker-compose logs -f phpmyadmin
```

### Stop Services

```bash
docker-compose stop
```

### Start Services Again

```bash
docker-compose start
```

### Stop and Remove Containers

```bash
docker-compose down
```

### Stop and Remove Everything (including data)

⚠️ **Warning**: This will delete all database data!

```bash
docker-compose down -v
```

### Restart Services

```bash
docker-compose restart
```

## Accessing MySQL from Command Line

### Connect to MySQL Container

```bash
docker exec -it fuel-management-mysql mysql -u root -p
```

Enter password: `rootpassword`

### Run SQL Commands

```sql
-- Show databases
SHOW DATABASES;

-- Use fuel management database
USE fuelmanagement;

-- Show tables
SHOW TABLES;

-- Example query
SELECT * FROM registered_vehicles LIMIT 10;
```

## Troubleshooting

### Port Already in Use

If port 3306 is already in use, you can change it in `docker-compose.yml`:

```yaml
ports:
  - "3307:3306"  # Use port 3307 instead
```

Then update your backend configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3307/fuelmanagement
```

### Database Not Accessible

1. Check if container is running:
   ```bash
   docker-compose ps
   ```

2. Check container logs:
   ```bash
   docker-compose logs mysql
   ```

3. Verify network connectivity:
   ```bash
   docker exec -it fuel-management-mysql mysqladmin ping -h localhost
   ```

### Import SQL Files Manually

If automatic import doesn't work:

```bash
# Copy SQL file to container
docker cp backend/fuel_stations_routines.sql fuel-management-mysql:/tmp/

# Execute SQL file
docker exec -it fuel-management-mysql mysql -u root -p fuelmanagement < /tmp/fuel_stations_routines.sql
```

Or import from host:

```bash
docker exec -i fuel-management-mysql mysql -u root -prootpassword fuelmanagement < backend/fuel_stations_routines.sql
```

### Reset Database

To start fresh:

```bash
# Stop and remove containers with volumes
docker-compose down -v

# Start again
docker-compose up -d
```

## Custom Configuration

### Change Database Credentials

Edit `docker-compose.yml`:

```yaml
environment:
  MYSQL_ROOT_PASSWORD: your_secure_password
  MYSQL_USER: your_username
  MYSQL_PASSWORD: your_password
```

Remember to update your backend `application.properties` accordingly.

### Persist Data to Custom Location

Edit `docker-compose.yml`:

```yaml
volumes:
  mysql_data:
    driver: local
    driver_opts:
      type: none
      o: bind
      device: /path/to/your/data/directory
```

## Production Considerations

For production deployments:

1. **Change default passwords** in `docker-compose.yml`
2. **Use environment variables** instead of hardcoded values
3. **Set up proper backups** for the database
4. **Use Docker secrets** for sensitive data
5. **Configure proper networking** and firewall rules
6. **Monitor container health** and logs
7. **Consider using** managed database services (AWS RDS, Google Cloud SQL, etc.)

## Next Steps

1. Continue with [SETUP.md](SETUP.md) to set up the backend and frontend
2. Read [CONTRIBUTING.md](CONTRIBUTING.md) if you want to contribute
3. Check the main [README.md](README.md) for project overview

---

**Need Help?** Check the troubleshooting section or open an issue on GitHub.
