# Security Policy

## Supported Versions

We release patches for security vulnerabilities for the following versions:

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |
| < 1.0   | :x:                |

## Reporting a Vulnerability

We take the security of Fuel Management System seriously. If you have discovered a security vulnerability, we appreciate your help in disclosing it to us in a responsible manner.

### Please DO NOT:
- Open a public GitHub issue for security vulnerabilities
- Disclose the vulnerability publicly before it has been addressed

### Please DO:
1. **Email** the project maintainers privately with details of the vulnerability
2. **Provide** detailed steps to reproduce the vulnerability
3. **Allow** reasonable time for the vulnerability to be addressed before public disclosure
4. **Work** with us to resolve the issue

### What to Include in Your Report

To help us understand and address the issue quickly, please include:

- Description of the vulnerability
- Steps to reproduce
- Potential impact
- Any possible mitigations you've identified
- Your contact information
- Any proof-of-concept code (if applicable)

## Security Best Practices

### For Developers

1. **Never commit sensitive data**
   - Use `.env` files for secrets (already in `.gitignore`)
   - Use the provided `application.properties.example` template
   - Never hardcode passwords, API keys, or tokens

2. **Keep dependencies updated**
   ```bash
   # Backend
   ./mvnw versions:display-dependency-updates
   
   # Frontend
   npm audit
   npm audit fix
   ```

3. **Use environment variables**
   - Store configuration in environment variables
   - Use the `.env.example` file as a template
   - Never commit `.env` files

4. **Database security**
   - Use strong passwords
   - Create separate database users with minimal privileges
   - Never use root user in production
   - Enable SSL/TLS for database connections in production

5. **API security**
   - Always validate input
   - Implement rate limiting
   - Use HTTPS in production
   - Keep JWT secrets secure and rotate them regularly

### For Deployment

1. **Change all default credentials** before deployment
2. **Use HTTPS/SSL** for all connections
3. **Configure firewall** rules properly
4. **Enable CORS** only for trusted domains
5. **Implement rate limiting** to prevent abuse
6. **Regular security audits** and penetration testing
7. **Keep all software updated** (OS, database, dependencies)
8. **Use secure headers** (HSTS, CSP, etc.)
9. **Implement proper logging** and monitoring
10. **Regular backups** with encryption

### Common Vulnerabilities to Avoid

#### SQL Injection
- ✅ **DO**: Use JPA/Hibernate with parameterized queries
- ❌ **DON'T**: Use string concatenation for SQL queries

#### Cross-Site Scripting (XSS)
- ✅ **DO**: Sanitize user input
- ✅ **DO**: Use Content Security Policy headers
- ❌ **DON'T**: Render unsanitized user input

#### Authentication/Authorization
- ✅ **DO**: Use secure password hashing (BCrypt)
- ✅ **DO**: Implement proper session management
- ✅ **DO**: Use JWT with proper expiration
- ❌ **DON'T**: Store passwords in plain text
- ❌ **DON'T**: Use weak password requirements

#### Sensitive Data Exposure
- ✅ **DO**: Use HTTPS for all API calls
- ✅ **DO**: Encrypt sensitive data at rest
- ✅ **DO**: Use environment variables for secrets
- ❌ **DON'T**: Log sensitive information
- ❌ **DON'T**: Expose stack traces in production

#### Broken Access Control
- ✅ **DO**: Implement role-based access control
- ✅ **DO**: Verify user permissions on every request
- ❌ **DON'T**: Trust client-side access control

### Dependency Security

#### Backend (Maven)
```bash
# Check for known vulnerabilities
./mvnw dependency-check:check

# Update dependencies
./mvnw versions:display-dependency-updates
./mvnw versions:use-latest-releases
```

#### Frontend (npm)
```bash
# Check for vulnerabilities
npm audit

# Fix vulnerabilities (be careful, test after)
npm audit fix

# Update dependencies
npm update

# Check for outdated packages
npm outdated
```

## Secure Configuration Checklist

### Before Production Deployment

- [ ] All default passwords changed
- [ ] `.env` file configured with production values
- [ ] `application.properties` configured for production
- [ ] HTTPS/SSL enabled
- [ ] Database user with minimal privileges created
- [ ] JWT secret key is strong and unique
- [ ] CORS configured for production domains only
- [ ] Rate limiting implemented
- [ ] Security headers configured
- [ ] Error messages don't expose sensitive information
- [ ] Logging configured (but not logging sensitive data)
- [ ] Backup strategy in place
- [ ] Monitoring and alerting set up
- [ ] Firewall rules configured
- [ ] All dependencies updated
- [ ] Security audit performed

## Security Headers

Configure these headers in your production deployment:

```
Strict-Transport-Security: max-age=31536000; includeSubDomains
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block
Content-Security-Policy: default-src 'self'
Referrer-Policy: strict-origin-when-cross-origin
```

## Contact

For security concerns, please contact the project maintainers directly via:
- GitHub: Open a private security advisory
- Email: Contact the repository owner

## Acknowledgments

We would like to thank the security researchers and contributors who help keep this project secure.

---

**Remember**: Security is everyone's responsibility. If you see something, say something!
