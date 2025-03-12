# User Management API

## 📌 Overview
This is a **Spring Boot REST API** for user management, handling CRUD operations with proper validation, error handling, and structured responses.

## 🚀 Features
- Create, Read, Update, and Delete (CRUD) operations for users.
- Uses `@Valid` to validate input.
- Structured JSON error responses.
- Uses Spring Data JPA for database operations.
- Implements logging for debugging.

## 🛠️ Setup Instructions

### Prerequisites
- Java 17+
- Maven
- PostgreSQL/MySQL (or use H2 for testing)

### Installation Steps
```bash
# Clone the repository
git clone https://github.com/example/user-management.git
cd user-management

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

## 🔗 API Endpoints

### Create a User
**POST** `/user-api/`
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 25
}
```
Response:
```json
{
  "message": "User created successfully with email: john@example.com"
}
```

### Get All Users
**GET** `/user-api/`
Response:
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "age": 25
  }
]
```

### Get User by ID
**GET** `/user-api/id/{userId}`

### Get User by Email
**GET** `/user-api/email/{userEmail}`

### Update User by ID
**PUT** `/user-api/update-user/id/{userId}`

### Delete User by ID
**DELETE** `/user-api/id/{userId}`

## 🛡️ Error Handling
Errors are returned in JSON format:
```json
{
  "error": "User not found with ID: 10"
}
```

## 🛠️ Future Improvements
- Implement JWT-based authentication.
- Add role-based access control.

---
Developed with ❤️ using Spring Boot.

