# Customer Rewards Service

A Spring Boot application that calculates reward points for customers based on their transaction history.

## Overview

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
- 2 points for every dollar spent over $100 in each transaction.
- 1 point for every dollar spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

This service provides APIs to manage customers and calculate their rewards over a three-month period.

## Technologies

- **Language:** Java 21
- **Framework:** Spring Boot 4.0.6
- **Database:** H2 (In-memory)
- **Library:** Lombok
- **Build Tool:** Maven

## Requirements

- JDK 21 or higher
- Maven (optional, use the provided wrapper)

## Setup and Run

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd customer-rewards-service
    ```

2.  **Build the application:**
    ```bash
    ./mvnw clean install
    ```

3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

The service will be available at `http://localhost:8080`.

## API Endpoints

### Rewards
- `POST /api/rewards/calculate`: Calculate rewards for a list of transactions provided in the request body.
- `GET /api/rewards/calculate/all`: Calculate rewards for all transactions stored in the database.

### Customers
- `GET /api/customers`: Retrieve all customers.
- `GET /api/customers/{id}`: Retrieve a specific customer.
- `POST /api/customers`: Create a new customer.
- `PUT /api/customers/{id}`: Update an existing customer.
- `DELETE /api/customers/{id}`: Delete a customer.

## Database Console

The H2 console is enabled and can be accessed at:
`http://localhost:8080/h2-console`

- **JDBC URL:** `jdbc:h2:mem:rewardsdb`
- **User:** `sa`
- **Password:** (leave blank)

## Scripts

- `./mvnw`: Maven wrapper script for Unix-like systems.
- `mvnw.cmd`: Maven wrapper script for Windows.

## Project Structure

```text
customer-rewards-service/
├── src/
│   ├── main/
│   │   ├── java/com/cap/rewards/
│   │   │   ├── controller/      # REST API Controllers
│   │   │   ├── model/           # Entity and DTO models
│   │   │   ├── repository/      # Data access repositories
│   │   │   ├── service/         # Business logic
│   │   │   └── CustomerRewardsServiceApplication.java # Entry point
│   │   └── resources/
│   │       ├── application.yml  # Application configuration
│   │       └── data.sql         # Initial data seed
│   └── test/                    # Unit and integration tests
├── pom.xml                      # Maven project descriptor
└── README.md
```

## Tests

To run the tests, execute:
```bash
./mvnw test
```

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| TODO | Add any environment variables used by the application | - |

## License

TODO: Add license information.
