# Case Java I - Product Management API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)

A RESTful API for product management built with Spring Boot and PostgreSQL. This project demonstrates modern Java development practices including REST API design, JPA/Hibernate data persistence, Docker containerization, and comprehensive testing.

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Testing](#-testing)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)

## ✨ Features

- **CRUD Operations**: Complete Create, Read, Update, and Delete operations for products
- **RESTful Design**: Follows REST API best practices with proper HTTP status codes
- **Data Validation**: Robust input validation and error handling
- **Database Integration**: PostgreSQL with JPA/Hibernate ORM
- **Docker Support**: Fully containerized application with Docker Compose
- **Health Monitoring**: Spring Boot Actuator for application health checks
- **Comprehensive Testing**: Unit tests with Mockito for business logic

## 🛠 Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Spring Boot | 3.3.0 | Application Framework |
| Spring Data JPA | 3.3.0 | Data Access Layer |
| Spring Web | 3.3.0 | REST API |
| Spring Actuator | 3.3.0 | Application Monitoring |
| PostgreSQL | 13 | Relational Database |
| Maven | 3.8.4+ | Build Tool |
| Docker | Latest | Containerization |
| JUnit 5 | 5.x | Testing Framework |
| Mockito | 5.x | Mocking Framework |

## 🏗 Architecture

The application follows a layered architecture pattern:

```
┌─────────────────────────────────────┐
│     Controller Layer (REST API)     │
├─────────────────────────────────────┤
│       Service Layer (Business)      │
├─────────────────────────────────────┤
│    Repository Layer (Data Access)   │
├─────────────────────────────────────┤
│         PostgreSQL Database         │
└─────────────────────────────────────┘
```

**Key Components:**
- **ProductController**: REST endpoints for product operations
- **ProductService**: Business logic and data transformation
- **ProductRepository**: Data access using Spring Data JPA
- **Product**: JPA entity representing the database table
- **ProductDTO**: Data Transfer Object for API responses

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Docker and Docker Compose
- Maven 3.8+ (optional, uses Maven Wrapper)

### Installation & Running

#### Option 1: Using Docker Compose (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/KayllaneGPina/case-java-I.git
   cd case-java-I
   ```

2. **Start the application**
   ```bash
   docker-compose up --build
   ```

3. **Access the API**
   ```
   http://localhost:8080/api/products
   ```

4. **Health Check**
   ```
   http://localhost:8080/actuator/health
   ```

#### Option 2: Local Development

1. **Start PostgreSQL**
   ```bash
   docker run -d \
     --name postgres-db \
     -e POSTGRES_DB=product_db \
     -e POSTGRES_USER=postgres \
     -e POSTGRES_PASSWORD=root123 \
     -p 5432:5432 \
     postgres:13
   ```

2. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

### Configuration

Database configuration can be modified in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/product_db
spring.datasource.username=postgres
spring.datasource.password=root123
spring.jpa.hibernate.ddl-auto=update
```

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/products
```

### Endpoints

#### 1. Get All Products
```http
GET /api/products
```

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Laptop",
    "price": 999.99,
    "description": "High-performance laptop"
  }
]
```

#### 2. Get Product by ID
```http
GET /api/products/{id}
```

**Response:** `200 OK` or `404 Not Found`
```json
{
  "id": 1,
  "name": "Laptop",
  "price": 999.99,
  "description": "High-performance laptop"
}
```

#### 3. Create Product
```http
POST /api/products
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Laptop",
  "price": 999.99,
  "description": "High-performance laptop"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Laptop",
  "price": 999.99,
  "description": "High-performance laptop"
}
```

#### 4. Update Product
```http
PUT /api/products/{id}
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Updated Laptop",
  "price": 1099.99,
  "description": "Updated description"
}
```

**Response:** `200 OK` or `404 Not Found`
```json
{
  "id": 1,
  "name": "Updated Laptop",
  "price": 1099.99,
  "description": "Updated description"
}
```

#### 5. Delete Product
```http
DELETE /api/products/{id}
```

**Response:** `204 No Content` or `404 Not Found`

### HTTP Status Codes

| Code | Description |
|------|-------------|
| 200 | OK - Request succeeded |
| 201 | Created - Resource created successfully |
| 204 | No Content - Resource deleted successfully |
| 404 | Not Found - Resource doesn't exist |

## 🧪 Testing

### Run All Tests
```bash
./mvnw test
```

### Run Specific Test Class
```bash
./mvnw test -Dtest=ProductControllerTest
```

### Test Coverage

The project includes comprehensive unit tests:
- **ProductControllerTest**: 8 test scenarios covering all REST endpoints
  - Success and error cases for all CRUD operations
  - Proper HTTP status code validation
  - Mocked service layer for isolated testing

### Build the Project
```bash
./mvnw clean package
```

## 📁 Project Structure

```
case-java-I/
├── src/
│   ├── main/
│   │   ├── java/br/com/prospertech/case_java/
│   │   │   ├── Controller/
│   │   │   │   └── ProductController.java
│   │   │   ├── Service/
│   │   │   │   └── ProductService.java
│   │   │   ├── Repository/
│   │   │   │   └── ProductRepository.java
│   │   │   ├── Model/
│   │   │   │   └── Product.java
│   │   │   ├── DTO/
│   │   │   │   └── ProductDTO.java
│   │   │   └── CaseJavaApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/br/com/prospertech/case_java/
│           └── Controller/
│               └── ProductControllerTest.java
├── Dockerfile
├── docker-compose.yaml
├── pom.xml
└── README.md
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Standards
- Follow Java naming conventions
- Write unit tests for new features
- Maintain code coverage above 80%
- Use constructor injection over field injection
- Follow REST API best practices

## 📝 License

This project is part of a technical assessment for Prospertech.

## 👥 Author

**Kayllane Gomes Pina**
- GitHub: [@KayllaneGPina](https://github.com/KayllaneGPina)

---

**Built with ❤️ using Spring Boot**