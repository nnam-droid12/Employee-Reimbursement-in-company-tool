# Employee Reimbursement in Company Tool

The Employee Reimbursement in Company Tool is an internal platform for managing employee expenses. It simplifies expense logging, approval workflows, and record searches within a company.

## Features

### For Employees:
- **Sign Up & Sign In**: Employees can create accounts and log in.
- **Log Expenses**: Submit expenses with details such as expense type, date, and amount.
- **Update Expenses**: Modify submitted expenses within a designated timeframe.
- **Search Expenses**: Retrieve expenses by month and year for easy tracking.

### For Admins:
- **Admin Login**: Admins log in using a default email and password.
- **Expense Notifications**: Receive notifications when employees submit expenses.
- **Approve/Reject Expenses**: Manage expense requests by approving or rejecting submissions.

## Technology Stack

- **Spring Boot**: Framework for building RESTful backend APIs.
- **MySQL**: Database for storing employee and expense details.
- **Redis**: In-memory cache for improved performance and scalability.
- **Docker**: Containerization for streamlined development and deployment.
- **Render**: Cloud platform for hosting the application.

## API Documentation

The API documentation is available through Swagger:
[Swagger API Documentation]
(https://employee-image-latest.onrender.com/swagger-ui/index.html)

## Installation and Setup

### Prerequisites

- Docker
- JDK 17 or later (for local development without Docker)

### Steps to Run Locally

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd employee-reimbursement-tool
   ```

2. Build and run the Docker containers:
   ```bash
   docker-compose up --build
   ```

3. Access the application locally:
   - API Base URL: `http://localhost:5050`
   - Swagger Documentation: `http://localhost:5050/swagger-ui/index.html`


### Configuration

Set the following environment variables in a `.env` file:

```env
DB_URL=your_mysql_url
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
REDIS_HOST=your_redis_host
REDIS_PORT=your_redis_port
DEFAULT_ADMIN_EMAIL=admin@example.com
DEFAULT_ADMIN_PASSWORD=admin_password
```

## Deployment

The application is deployed on Render. Follow these steps to deploy:

1. Build Docker images:
   ```bash
   docker build -t employee-reimbursement-tool .
   ```

2. Push images to a container registry.

3. Deploy on Render by linking the repository and configuring environment variables.

## License

This project is licensed under the MIT License.

## Contact

For queries, feel free to reach out:
- **Email**: [williamekene700@gmail.com](mailto:williamekene700@gmail.com)
- **GitHub Issues**: [Report an Issue](<repository-url>/issues)

