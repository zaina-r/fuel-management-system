# Contributing to Fuel Management System

Thank you for your interest in contributing to the Fuel Management System! This document provides guidelines and instructions for contributing to this project.

## Table of Contents
- [Getting Started](#getting-started)
- [Development Workflow](#development-workflow)
- [Code Standards](#code-standards)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)
- [Reporting Issues](#reporting-issues)

## Getting Started

1. **Fork the Repository**
   - Click the "Fork" button at the top right of the repository page
   - Clone your fork locally:
     ```bash
     git clone https://github.com/YOUR_USERNAME/fuel-management-system.git
     cd fuel-management-system
     ```

2. **Set Up Development Environment**
   - Follow the [SETUP.md](SETUP.md) guide to set up your local environment
   - Ensure all tests pass before making changes:
     ```bash
     cd backend
     ./mvnw test
     ```

3. **Create a Branch**
   - Create a new branch for your feature or bug fix:
     ```bash
     git checkout -b feature/your-feature-name
     # or
     git checkout -b fix/bug-description
     ```

## Development Workflow

### Branch Naming Convention

Use descriptive branch names with prefixes:
- `feature/` - for new features (e.g., `feature/vehicle-qr-generation`)
- `fix/` - for bug fixes (e.g., `fix/login-validation-error`)
- `docs/` - for documentation updates (e.g., `docs/api-documentation`)
- `refactor/` - for code refactoring (e.g., `refactor/auth-service`)
- `test/` - for adding tests (e.g., `test/fuel-allocation`)

### Making Changes

1. **Keep Changes Focused**
   - Make small, focused commits
   - Each commit should represent a single logical change
   - Don't mix unrelated changes in the same PR

2. **Write Clean Code**
   - Follow the existing code style
   - Add comments for complex logic
   - Remove commented-out code and debug logs

3. **Test Your Changes**
   - Write unit tests for new features
   - Ensure existing tests still pass
   - Test manually in the UI when applicable

## Code Standards

### Backend (Java/Spring Boot)

- Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- Use meaningful variable and method names
- Keep methods small and focused (single responsibility)
- Use Lombok annotations to reduce boilerplate
- Handle exceptions appropriately
- Write Javadoc for public methods

Example:
```java
/**
 * Validates vehicle registration details against the DMV database.
 *
 * @param vehicleNumber the vehicle number to validate
 * @param chassisNumber the chassis number to validate
 * @return true if valid, false otherwise
 * @throws VehicleValidationException if validation fails
 */
public boolean validateVehicle(String vehicleNumber, String chassisNumber) {
    // Implementation
}
```

### Frontend (React)

- Use functional components with hooks
- Follow [Airbnb React Style Guide](https://github.com/airbnb/javascript/tree/master/react)
- Use meaningful component and variable names
- Keep components small and reusable
- Use PropTypes or TypeScript for type checking
- Use Tailwind CSS for styling (avoid inline styles)

Example:
```javascript
// Good
const VehicleCard = ({ vehicle, onSelect }) => {
  const handleClick = () => {
    onSelect(vehicle.id);
  };

  return (
    <div className="p-4 border rounded-lg hover:shadow-lg" onClick={handleClick}>
      <h3 className="text-lg font-bold">{vehicle.number}</h3>
      <p className="text-gray-600">{vehicle.type}</p>
    </div>
  );
};
```

### Mobile App (React Native)

- Follow React Native best practices
- Use platform-specific code when necessary
- Optimize for performance (avoid unnecessary re-renders)
- Test on both Android and iOS when possible

### Database

- Use meaningful table and column names
- Add appropriate indexes
- Document schema changes
- Provide migration scripts if needed

## Commit Guidelines

### Commit Message Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Type:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, no logic change)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

**Examples:**
```
feat(auth): add two-factor authentication

Implemented 2FA using OTP sent via email. Users can enable/disable
2FA in their profile settings.

Closes #123
```

```
fix(fuel): correct weekly quota calculation

Fixed bug where quota wasn't resetting properly on Sunday midnight.
Changed cron expression to ensure accurate reset timing.

Fixes #456
```

### Commit Best Practices

- Use present tense ("add feature" not "added feature")
- Use imperative mood ("move cursor to..." not "moves cursor to...")
- Keep subject line under 50 characters
- Capitalize the subject line
- Don't end the subject line with a period
- Separate subject from body with a blank line
- Wrap body at 72 characters
- Explain what and why, not how

## Pull Request Process

### Before Submitting

1. **Update Documentation**
   - Update README.md if adding new features
   - Add/update API documentation
   - Update SETUP.md if changing setup process

2. **Run Tests**
   ```bash
   # Backend tests
   cd backend
   ./mvnw test

   # Frontend tests (if available)
   cd frontend/reactapp
   npm test
   ```

3. **Check Build**
   ```bash
   # Backend
   ./mvnw clean package

   # Frontend
   npm run build
   ```

4. **Review Your Changes**
   ```bash
   git diff main
   ```

### Submitting Pull Request

1. **Push Your Branch**
   ```bash
   git push origin feature/your-feature-name
   ```

2. **Create Pull Request**
   - Go to the repository on GitHub
   - Click "New Pull Request"
   - Select your branch
   - Fill in the PR template

3. **PR Description Should Include:**
   - Summary of changes
   - Related issue numbers (e.g., "Closes #123")
   - Testing performed
   - Screenshots (for UI changes)
   - Breaking changes (if any)

### PR Template Example

```markdown
## Description
Brief description of what this PR does.

## Related Issue
Closes #123

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests added/updated
- [ ] Manual testing performed
- [ ] All tests passing

## Screenshots (if applicable)
Add screenshots here

## Checklist
- [ ] Code follows project style guidelines
- [ ] Self-reviewed the code
- [ ] Commented complex code sections
- [ ] Updated documentation
- [ ] No new warnings
- [ ] Added tests
- [ ] All tests pass
```

### Code Review Process

- Be open to feedback
- Respond to comments promptly
- Make requested changes
- Re-request review after updates

## Reporting Issues

### Before Creating an Issue

1. Search existing issues to avoid duplicates
2. Check if it's already fixed in the latest version
3. Gather relevant information (error messages, logs, screenshots)

### Creating a Good Issue

Include:
- **Clear Title**: Descriptive and specific
- **Description**: What happened vs. what you expected
- **Steps to Reproduce**: Detailed steps
- **Environment**: OS, browser, versions
- **Screenshots/Logs**: Visual evidence or error logs
- **Possible Solution**: If you have ideas

### Issue Template Example

```markdown
## Description
A clear description of the bug or feature request.

## Steps to Reproduce
1. Go to '...'
2. Click on '...'
3. See error

## Expected Behavior
What should happen.

## Actual Behavior
What actually happens.

## Environment
- OS: [e.g., Windows 10, macOS 12]
- Browser: [e.g., Chrome 95, Firefox 94]
- Node version: [e.g., 18.x]
- Java version: [e.g., 17]

## Screenshots
Add screenshots if applicable.

## Additional Context
Any other relevant information.
```

## Code of Conduct

- Be respectful and inclusive
- Welcome newcomers
- Give constructive feedback
- Focus on the code, not the person
- Respect different viewpoints
- Assume good intentions

## Questions?

If you have questions about contributing:
- Check existing documentation
- Ask in GitHub Discussions
- Contact the maintainers

## Recognition

Contributors will be acknowledged in the project README and release notes.

---

Thank you for contributing to the Fuel Management System! 🚀
