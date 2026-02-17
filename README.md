# LOS System (Loan Origination System)

## Project Overview

The **LOS System** is a comprehensive **Loan Origination System** built with **Spring Boot** and **Java 22**. This system automates and manages the entire loan application lifecycle from initial customer application to loan approval and disbursement. The system integrates with external verification services (Aadhar, PAN, CIBIL) to verify customer identity and assess creditworthiness.

## What This Project Does

This LOS System is designed to handle the complete workflow of processing loan applications in a financial institution. It provides:

1. **Customer Onboarding**: Captures customer details, address, and income information
2. **Document Verification**: Validates customer identity through Aadhar and PAN cards
3. **Credit Assessment**: Evaluates creditworthiness using CIBIL scores and credit history
4. **Loan Processing**: Approves or rejects loan applications based on predefined criteria
5. **Loan Disbursement**: Calculates EMI schedules for approved loans

## Project Architecture

The system follows a **multi-module Maven architecture** with clear separation of concerns:

```
los-system/
├── common/              # Common services and configurations
├── data-model/          # JPA entities and form models
├── data/                # Data access layer (DAOs)
├── pl-services/         # Business logic and REST controllers
└── pom.xml             # Parent POM configuration
```

### Module Details

#### 1. **data-model** Module
- **Purpose**: Contains all entity classes and form objects
- **Key Components**:
  - **Entities**: `Customer`, `LoanApplication`, `Address`, `IncomeDetails`, `AadharDetails`, `PanDetails`, `CibilDetails`
  - **Forms**: Data transfer objects for API requests/responses
  - **Enums**: Status enums for loan application states

#### 2. **data** Module
- **Purpose**: Data access layer with DAO interfaces and implementations
- **Key Components**:
  - `CustomerDao`: Customer CRUD operations
  - `LoanDetailsDao`: Loan application data operations
  - `AddressDao`: Address management
  - `IncomeDetailsDao`: Income information management
  - `DataVerificationDao`: Document verification data operations

#### 3. **common** Module
- **Purpose**: Shared services and external API integrations
- **Key Components**:
  - `AadharService`: Integration with Aadhar verification API
  - `PanService`: Integration with PAN verification API
  - `CibilService`: Integration with CIBIL credit bureau
  - `ApplicationConfiguration`: Spring configuration

#### 4. **pl-services** Module
- **Purpose**: Main application with business logic and REST APIs
- **Key Components**:
  - `LOSApplication`: Spring Boot main application class
  - REST Controllers for all loan processing workflows
  - Service implementations for business logic

## Key Features

### 1. Loan Application Creation
- Accepts customer details, loan requirements, and income information
- Creates a loan application with status `PENDING`
- Stores customer, address, and income details in the database

### 2. Data Verification
- Verifies customer identity using **Aadhar card** details
- Validates PAN card information
- Fetches CIBIL credit report
- All verification data is stored for audit purposes

### 3. Credit Assessment
- Evaluates creditworthiness based on:
  - **CIBIL Score**: Minimum score requirements
  - **Credit Status**: GOOD, AVERAGE, or POOR
  - **Active Accounts**: Maximum limit of 5 active accounts
  - **Credit Enquiries**: Maximum limit of 5 enquiries
  - **Debt-to-Income Ratio**: EMI should not exceed 50% of monthly income
  - **Outstanding Loans**: Checks existing loan obligations

### 4. Loan Processing
- **Approve Loan**: Changes status to `APPROVED`, sets approval amount
- **Reject Loan**: Changes status to `REJECTED`, marks application inactive
- Updates loan application status based on assessment results

### 5. EMI Calculation & Disbursement
- Calculates monthly EMI schedule
- Provides detailed breakdown of principal and interest
- Returns complete EMI list for the loan tenure

## Technology Stack

- **Backend Framework**: Spring Boot 3.5.0
- **Language**: Java 22
- **Build Tool**: Maven
- **Database**: MySQL 8.x
- **ORM**: Hibernate/JPA
- **Web**: Spring Web (REST APIs)
- **Dependencies**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `mysql-connector-j`
  - `jakarta.persistence-api`

## Database Schema

### Core Tables

1. **customers**
   - Customer personal information (name, email, phone, Aadhar, PAN, DOB)

2. **address**
   - Customer address details (street, city, state, zip code, country)

3. **income_details**
   - Employment and income information

4. **loan_applications**
   - Loan application details (amount, type, status, term, purpose)

5. **aadhar_details**
   - Verified Aadhar card information

6. **pan_details**
   - Verified PAN card information

7. **cibil_details**
   - Credit bureau report (score, active accounts, enquiries, outstanding loans)

### Status Enum Values
- `PENDING`: Initial state
- `IN_PROCESS`: Under review
- `APPROVED`: Loan approved
- `REJECTED`: Loan rejected

## REST API Endpoints

### API v1: Loan Application Management
**Base Path**: `/los/api/v1`

- `POST /createLoanApplication` - Create new loan application
- `GET /getLoanApplicationById?loanApplicationId={id}` - Retrieve loan application

### API v2: Data Verification
**Base Path**: `/los/api/v2`

- `GET /getCustomerData?loanApplicationId={id}` - Verify customer documents (Aadhar, PAN, CIBIL)

### API v3: Credit Assessment
**Base Path**: `/los/api/v3`

- `GET /creditAssessment?loanApplicationId={id}` - Assess creditworthiness

### API v4: Loan Processing
**Base Path**: `/los/api/v4`

- `GET /approveLoan?loanApplicationId={id}&requestedAmount={amount}` - Approve loan
- `GET /rejectLoan?loanApplicationId={id}` - Reject loan

### API v5: Loan Disbursement
**Base Path**: `/los/api/v5`

- `GET /getEmisList?loanApplicationId={id}` - Get EMI schedule

### API v6: CIBIL Data Update
**Base Path**: `/los/api/v6`

- `GET /updateCibilDataByloanApplicationId?loanApplicationId={id}` - Update CIBIL information

## Loan Processing Workflow

```
1. Customer submits loan application
   ↓
2. System creates customer and loan records (Status: PENDING)
   ↓
3. Data Verification Service validates:
   - Aadhar details
   - PAN details
   - CIBIL report
   ↓
4. Credit Assessment Service evaluates:
   - Credit score
   - Active accounts
   - Debt-to-income ratio
   - Credit enquiries
   ↓
5. Loan Processing:
   - If eligible → APPROVED
   - If not eligible → REJECTED
   ↓
6. Loan Disbursement (if approved):
   - Calculate EMI schedule
   - Generate payment plan
```

## Setup and Installation

### Prerequisites
- Java 22 or higher
- Maven 3.6+
- MySQL 8.x
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Database Setup

1. Create MySQL database:
```sql
CREATE DATABASE los;
```

2. Run the SQL script to create tables:
```bash
mysql -u root -p los < los-sql.sql
```

### Application Configuration

Edit `pl-services/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/los
spring.datasource.username=your_username
spring.datasource.password=your_password
server.port=8084
server.servlet.context-path=/los
```

### Build and Run

1. **Build the project**:
```bash
mvn clean install
```

2. **Run the application**:
```bash
cd pl-services
mvn spring-boot:run
```

3. **Access the application**:
```
http://localhost:8084/los
```

## Sample Request

### Create Loan Application
```json
POST http://localhost:8084/los/api/v1/createLoanApplication

{
  "customer": {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "+1234567890",
    "aadharNumber": "123456789012",
    "panNumber": "ABCDE1234F",
    "dob": "1990-01-01",
    "address": {
      "street": "123 Elm Street",
      "city": "New York",
      "state": "NY",
      "zipCode": "10001",
      "country": "USA"
    }
  },
  "loanDetails": {
    "loanType": "Personal Loan",
    "loanAmount": "100000.00",
    "loanTermInMonths": "24",
    "loanPurpose": "Home Renovation"
  },
  "incomeDetails": {
    "monthlyIncome": "5000.00",
    "employmentStatus": "Employed",
    "employerName": "XYZ Corp",
    "yearsAtJob": "3"
  }
}
```

## Credit Assessment Criteria

The system uses the following criteria for loan approval:

1. **CIBIL Score**: Must be in GOOD or AVERAGE range
2. **Active Accounts**: Maximum 5 active loan accounts
3. **Credit Enquiries**: Maximum 5 recent enquiries
4. **Debt-to-Income Ratio**: Total EMIs should not exceed 50% of monthly income
5. **Outstanding Loans**: Considered in overall assessment

## Project Structure Benefits

### Modularity
- Clear separation between data layer, business logic, and presentation
- Easy to maintain and extend individual modules

### Scalability
- Multi-module architecture allows independent scaling
- Services can be extracted into microservices if needed

### Reusability
- Common module provides shared functionality
- Data models are centralized and reusable

### Testability
- Each module can be tested independently
- Clear interfaces between modules

## Future Enhancements

Potential improvements for the system:

1. **Authentication & Authorization**: Add Spring Security for API security
2. **Document Upload**: Allow customers to upload documents
3. **Notifications**: Email/SMS notifications for status updates
4. **Admin Dashboard**: Web UI for loan officers
5. **Reporting**: Generate loan reports and analytics
6. **Audit Logging**: Track all changes to loan applications
7. **Workflow Engine**: Configurable approval workflows
8. **Integration Tests**: Comprehensive test coverage
9. **API Documentation**: Swagger/OpenAPI documentation
10. **Caching**: Redis cache for frequently accessed data

## Contributing

This is a loan origination system for financial institutions. Contributions should follow:
- Clean code principles
- Proper error handling
- Comprehensive logging
- Security best practices

## License

[Add appropriate license information]

## Contact

For questions or support, contact the development team.

---

**Note**: This system integrates with external services (Aadhar, PAN, CIBIL). Ensure proper API credentials and compliance with data protection regulations before deployment.
