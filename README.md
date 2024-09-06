# Spring Boot Application with Spring Security

This is a simple Spring Boot application that demonstrates the integration of Spring Security for handling authentication and authorization with a PostgreSQL database.

## Features

- Spring Boot 3.x.x
- Spring Security 6.x.x
- User authentication and authorization
- Secured endpoints
- Form-based login
- Role-based access control
- PostgreSQL for database management

## Requirements

- Java 17+
- Maven 4.0.0
- PostgreSQL
- Your preferred IDE (IntelliJ, Eclipse, VSCode, etc.)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/spring-boot-security-app.git
cd spring-boot-security-app
```

### 2. PostgreSQL Database Setup

Before running the application, ensure you have PostgreSQL installed and a database set up. Follow these steps:

1.	Install PostgreSQL on your system if you don’t already have it. You can download it from the official website: https://www.postgresql.org/download/
2.	Create a new PostgreSQL database and user.

```postgresql
CREATE DATABASE your_database;
CREATE USER your_username WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE your_database TO your_username;
```

### 3. Configure application.properties

You need to configure the database connection settings in src/main/resources/application.properties to connect your Spring Boot app to the PostgreSQL database.

```java
# PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate Configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Spring Security Default User (Optional)
spring.security.user.name=user
spring.security.user.password=password
```