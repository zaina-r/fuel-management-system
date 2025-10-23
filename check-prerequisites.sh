#!/bin/bash

# Fuel Management System - Environment Check Script
# This script checks if all prerequisites are installed

echo "======================================"
echo "Fuel Management System - Prerequisites Check"
echo "======================================"
echo ""

# Color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Track if all checks pass
ALL_CHECKS_PASSED=true

# Function to check command existence
check_command() {
    local cmd=$1
    local name=$2
    local min_version=$3
    
    if command -v $cmd &> /dev/null; then
        version=$($cmd --version 2>&1 | head -n1)
        echo -e "${GREEN}✓${NC} $name is installed: $version"
        return 0
    else
        echo -e "${RED}✗${NC} $name is NOT installed"
        echo -e "  ${YELLOW}Please install $name (version $min_version or higher)${NC}"
        ALL_CHECKS_PASSED=false
        return 1
    fi
}

# Function to check Java version
check_java() {
    if command -v java &> /dev/null; then
        version=$(java -version 2>&1 | head -n1 | cut -d'"' -f2)
        major_version=$(echo $version | cut -d'.' -f1)
        
        if [ "$major_version" -ge 17 ]; then
            echo -e "${GREEN}✓${NC} Java is installed: $version"
            return 0
        else
            echo -e "${YELLOW}⚠${NC} Java $version is installed, but version 17+ is required"
            ALL_CHECKS_PASSED=false
            return 1
        fi
    else
        echo -e "${RED}✗${NC} Java is NOT installed"
        echo -e "  ${YELLOW}Please install Java 17 or higher${NC}"
        ALL_CHECKS_PASSED=false
        return 1
    fi
}

# Function to check Node.js version
check_node() {
    if command -v node &> /dev/null; then
        version=$(node --version | cut -d'v' -f2)
        major_version=$(echo $version | cut -d'.' -f1)
        
        if [ "$major_version" -ge 18 ]; then
            echo -e "${GREEN}✓${NC} Node.js is installed: v$version"
            return 0
        else
            echo -e "${YELLOW}⚠${NC} Node.js v$version is installed, but version 18+ is recommended"
            return 0
        fi
    else
        echo -e "${RED}✗${NC} Node.js is NOT installed"
        echo -e "  ${YELLOW}Please install Node.js 18 or higher${NC}"
        ALL_CHECKS_PASSED=false
        return 1
    fi
}

# Function to check MySQL
check_mysql() {
    if command -v mysql &> /dev/null; then
        version=$(mysql --version | awk '{print $5}' | tr -d ',')
        echo -e "${GREEN}✓${NC} MySQL client is installed: $version"
        
        # Try to connect to MySQL
        if mysql -u root -e "SELECT 1" &> /dev/null; then
            echo -e "${GREEN}✓${NC} MySQL server is accessible (no password)"
            return 0
        else
            echo -e "${YELLOW}⚠${NC} MySQL server requires password or is not running"
            echo -e "  ${YELLOW}Make sure MySQL server is running${NC}"
        fi
        return 0
    else
        echo -e "${RED}✗${NC} MySQL is NOT installed"
        echo -e "  ${YELLOW}Please install MySQL 8 or use Docker${NC}"
        ALL_CHECKS_PASSED=false
        return 1
    fi
}

# Function to check Docker
check_docker() {
    if command -v docker &> /dev/null; then
        version=$(docker --version | awk '{print $3}' | tr -d ',')
        echo -e "${GREEN}✓${NC} Docker is installed: $version"
        
        # Check if Docker daemon is running
        if docker ps &> /dev/null; then
            echo -e "${GREEN}✓${NC} Docker daemon is running"
        else
            echo -e "${YELLOW}⚠${NC} Docker daemon is not running"
            echo -e "  ${YELLOW}Please start Docker${NC}"
        fi
        
        # Check Docker Compose
        if docker compose version &> /dev/null; then
            compose_version=$(docker compose version | awk '{print $4}')
            echo -e "${GREEN}✓${NC} Docker Compose is installed: $compose_version"
        else
            echo -e "${YELLOW}⚠${NC} Docker Compose is not available"
        fi
        return 0
    else
        echo -e "${YELLOW}⚠${NC} Docker is NOT installed (optional, but recommended)"
        echo -e "  ${YELLOW}Install Docker for easier database setup${NC}"
        return 0
    fi
}

# Function to check Git
check_git() {
    if command -v git &> /dev/null; then
        version=$(git --version | awk '{print $3}')
        echo -e "${GREEN}✓${NC} Git is installed: $version"
        return 0
    else
        echo -e "${RED}✗${NC} Git is NOT installed"
        echo -e "  ${YELLOW}Please install Git${NC}"
        ALL_CHECKS_PASSED=false
        return 1
    fi
}

echo "Checking required software..."
echo ""

# Run all checks
echo "Core Requirements:"
check_java
check_node
check_command "npm" "npm" "9.0.0"
check_git
echo ""

echo "Database:"
check_mysql
echo ""

echo "Optional (but recommended):"
check_docker
echo ""

# Check if in correct directory
if [ -f "backend/pom.xml" ] && [ -f "frontend/reactapp/package.json" ]; then
    echo -e "${GREEN}✓${NC} You are in the correct project directory"
else
    echo -e "${YELLOW}⚠${NC} You might not be in the project root directory"
    echo -e "  ${YELLOW}Please run this script from the fuel-management-system directory${NC}"
fi

echo ""
echo "======================================"

if [ "$ALL_CHECKS_PASSED" = true ]; then
    echo -e "${GREEN}All required checks passed!${NC}"
    echo ""
    echo "Next steps:"
    echo "1. Review SETUP.md for detailed instructions"
    echo "2. Set up your database (see DOCKER.md for quick start)"
    echo "3. Configure backend/src/main/resources/application.properties"
    echo "4. Run the backend and frontend"
else
    echo -e "${RED}Some checks failed!${NC}"
    echo ""
    echo "Please install the missing software before proceeding."
    echo "Refer to SETUP.md for installation instructions."
fi

echo "======================================"
