# Library Management System

A comprehensive Spring Boot REST API for managing a library's books, borrowers, and borrowing lifecycle.

## 🚀 Features

### Core Features
- **Book Management**: Add, update, delete (soft), and search books with pagination and filtering
- **Borrower Management**: Register borrowers with membership tiers (BASIC, PREMIUM)
- **Borrowing Workflow**: Complete borrow/return cycle with validation and fine calculation
- **Fine Management**: Automatic fine calculation based on category-specific policies
- **Transaction Management**: Atomic operations for borrowing and returning books

### Advanced Features
- **Analytics APIs**:
    - Top borrowed books
    - Borrower activity statistics
    - Book availability summary
    - Similar book recommendations
- **Caching**: Redis-like caching for frequently accessed data
- **Global Exception Handling**: Consistent error responses
- **API Documentation**: Interactive Swagger/OpenAPI docs

## 🛠️ Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Lombok**
- **SpringDoc OpenAPI (Swagger)**
- **Gradle**

## 📋 Prerequisites

- JDK 17 or higher
- Gradle 7.0 or higher
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Installation & Setup

### 1. Clone the repository
```bash
git clone <repository-url>
cd library-management
```

### 2. Build the project
```bash
 ./gradlew clean build
```

### 3. Run the application
```bash
    ./gradlew bootRun
```

The application will start on `http://localhost:8080`

### 4. Access H2 Console
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:librarydb`
- Username: `test`
- Password: `geekyant`

### 5. Access API Documentation
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/api-docs`

##  API Endpoints

### Book Management

#### Add Book
```http
POST /books
Content-Type: application/json

{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "category": "TECH",
  "totalCopies": 5
}
```

#### Get Books (with filters)
```http
GET /books?category=TECH&available=true&page=0&size=10&sortBy=title
```

#### Update Book
```http
PUT /books/{id}
Content-Type: application/json

{
  "totalCopies": 7,
  "availableCopies": 5
}
```

#### Delete Book (Soft Delete)
```http
DELETE /books/{id}
```

#### Get Similar Books
```http
GET /books/similar/{id}
```

#### Get Availability Summary
```http
GET /books/availability-summary
```

### Borrower Management

#### Register Borrower
```http
POST /borrowers
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "membershipType": "PREMIUM"
}
```

#### Get Borrower Records
```http
GET /borrowers/{id}/records
```

#### Get Overdue Borrowers
```http
GET /borrowers/overdue
```

### Borrowing Workflow

#### Borrow Book
```http
POST /borrow
Content-Type: application/json

{
  "bookId": "uuid-here",
  "borrowerId": "uuid-here"
}
```

#### Return Book
```http
POST /return
Content-Type: application/json

{
  "recordId": "uuid-here"
}
```

#### Get Active Records
```http
GET /records/active
```

### Analytics

#### Top Borrowed Books
```http
GET /analytics/top-borrowed-books?limit=5
```

#### Borrower Activity
```http
GET /analytics/borrower-activity
```

## 🔐 Business Rules

1. **Borrowing Limits**:
    - BASIC members: 2 books maximum
    - PREMIUM members: 5 books maximum

2. **Loan Duration**: 14 days from borrow date

3. **Fine Calculation**:
    - Fines applied if book returned after due date
    - Fine amount = days_late × fine_per_day (category-specific)
    - Default fine: $5.00 per day

4. **Book Availability**:
    - Books can only be borrowed if available copies > 0
    - Available copies updated atomically during borrow/return

5. **Soft Delete**:
    - Books cannot be deleted if active borrow records exist
    - Deleted books are marked as deleted, not removed from database

## 🧪 Testing

### Sample Test Flow

1. **Register a borrower**
2. **Add books to the library**
3. **Borrow a book**
4. **Check active records**
5. **Return the book (late to test fines)**
6. **View borrower history**

### Using cURL

```bash
# Register Borrower
curl -X POST http://localhost:8080/borrowers \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","email":"test@example.com","membershipType":"PREMIUM"}'

# Add Book
curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Book","author":"Test Author","category":"FICTION","totalCopies":3}'

# Borrow Book
curl -X POST http://localhost:8080/borrow \
  -H "Content-Type: application/json" \
  -d '{"bookId":"book-uuid","borrowerId":"borrower-uuid"}'
```


##  Error Handling

All errors return a consistent JSON structure:

```json
{
  "message": "Error description",
  "status": 404,
  "timestamp": "2025-10-30",
  "path": "/api/endpoint"
}
```