# Travel Journal (TJ) Backend

A Spring Boot backend application for a travel journal platform that helps users document and share their travel experiences.

## Features

- User management and authentication
- Travel diary creation and management
- Location-based content organization
- Route planning and time-axis calculations
- Content recommendations
- Feed flow with pagination
- Memory flashback video generation
- Collaborative sharing features
- Export functionality

## Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MariaDB
- Redis
- Swagger/OpenAPI
- Maven

## Prerequisites

- JDK 21
- Maven 3.6+
- MariaDB 10.6+
- Redis 6.2+

## Getting Started

1. Clone the repository
2. Configure the database connection in `application.yml`
3. Start Redis server
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Documentation

Once the application is running, you can access the Swagger UI documentation at:
```
http://localhost:8080/api/swagger-ui.html
```

## Project Structure

```
tj
 ┣ src/main/java
 ┃ ┗ com/gs/tj
 ┃   ┣ controller    # REST controllers
 ┃   ┣ entity       # JPA entities
 ┃   ┣ repository   # Data access objects
 ┃   ┣ service      # Business logic
 ┃   ┃ ┗ impl      # Service implementations
 ┃   ┣ config      # Configuration classes
 ┃   ┗ TjApplication.java
 ┗ src/main/resources
   ┣ application.yml
   ┗ ...
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details. 