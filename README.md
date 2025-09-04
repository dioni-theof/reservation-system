# Reservation System

A comprehensive reservation management system built with Spring Boot and Java,
designed to handle booking operations and resource management efficiently.
The system provides RESTful APIs for managing reservations, resources, and user bookings.

## 🚀 Features

- Reservation Management
    - Create, read, update, and delete reservations
    - Search reservations by ID or date
    - Paginated reservation listing
- Resource Management
    - Resource availability tracking
    - Resource capacity management
    - Resource scheduling
- User Management
    - User registration and profile management
    - Booking history tracking
    - User preferences
- RESTful API endpoints
- Pagination support for large datasets
- Docker support for easy deployment
- PostgreSQL database integration

## 🛠️ Technologies

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- SpringDoc OpenAPI
- PostgreSQL
- Docker
- Maven
- JSON for API communication

## 📋 Prerequisites

- Java 21 or higher
- Docker and Docker Compose
- Maven
- PostgreSQL (provided via Docker)

## 🔧 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/dioni-theof/reservation-system.git
   cd reservation-system
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Start the application with Docker:
   ```bash
   docker-compose up
   ```

The application will be available at `http://localhost:8080`

## 🏗️ Project Structure

```
reservation-system/
├── src/
│   ├── main/
│   │   ├── java/org/sonja/reservation_system/
│   │   │   ├── controller/         # REST API endpoints
│   │   │   ├── dto/                # Data transfer objects                  
│   │   │   ├── entities/           # JPA entities
│   │   │   ├── exeptions/          # Handle exceptions & Custom exceptions
│   │   │   ├── Mappers/            # Object mapping utilities
│   │   │   ├── repo/               # Data repositories
│   │   │   ├── service/            # Business logic
│   │   │   └── ReservationSystemApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/org/sonja/reservation_system/
│       └── ReservationSystemApplicationTests.java
├── .mvn/ # maven wrapper
├── https/ # examples http requests
├── docker-compose.yml
└── pom.xml
```

## 🚀 API Endpoints

### Reservations
- `POST /api/reservations` - Create a new reservation
    - Request body: ReservationRequest (resourceId, userId, startTime, endTime)
    - Creates a new reservation in the system
- `GET /api/reservations/all` - Get all reservations
    - Query parameters:
        - size (default: 5) - Items per page
        - page (default: 0) - Page number
    - Returns paginated list of reservations
- `GET /api/reservations/{id}` - Find reservation by ID
    - Path parameter: id
    - Returns detailed reservation information
- `PUT /api/reservations/{id}` - Update reservation
    - Path parameter: id
    - Request body: UpdateReservationRequest
    - Updates reservation information
- `DELETE /api/reservations/{id}` - Delete reservation
    - Path parameter: id
    - Removes the reservation from the system
    - Returns success message

### Resources
- `POST /api/resources` - Create a new resource
    - Request body: ResourceRequest (name, type, capacity)
    - Creates a new resource in the system
- `GET /api/resources/available` - Get available resources
    - Query parameters: startTime, endTime
    - Returns list of available resources for the specified time period
- `PUT /api/resources/{id}` - Update resource information
    - Request body: ResourceRequest (can update name, type, capacity)
- `DELETE /api/resources/{id}` - Delete a resource
    - Removes the resource and associated reservations

## 📝 Documentation

API documentation is available at `http://localhost:8080/swagger-ui.html` when running the application.