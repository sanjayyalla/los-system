drop database los;
CREATE DATABASE los;
use los;


CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150),
    phone_number VARCHAR(20),
    aadhar_number VARCHAR(20) UNIQUE,
    pan_number VARCHAR(20) UNIQUE,
    dob DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE address (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(50),
    zip_code VARCHAR(20),
    country VARCHAR(100),
    customer_id INT,
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE income_details (
    income_id INT PRIMARY KEY AUTO_INCREMENT,
    monthly_income DECIMAL(10,2),
    employment_status VARCHAR(50),
    employer_name VARCHAR(100),
    years_at_job INT,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
CREATE TABLE loan_applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    loan_amount DECIMAL(15,2),
    loan_type VARCHAR(50),
    application_date DATE,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'IN_PROCESS'),
    approval_amount DECIMAL(15,2),
    is_active BOOLEAN,
    loan_term_in_months INT,
    loan_purpose varchar(255),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE aadhar_details (
    aadhar_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    aadhar_number VARCHAR(20) UNIQUE,
    full_name VARCHAR(200),
    gender VARCHAR(10),
    dob DATE,
    father_name VARCHAR(200),
    street VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    pincode VARCHAR(10),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE pan_details (
    pan_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    pan_number VARCHAR(20) UNIQUE,
    full_name VARCHAR(200),
    dob DATE,
    father_name VARCHAR(200),
    gender VARCHAR(10),
    status VARCHAR(20),
    issued_on DATE,
    category VARCHAR(50),
    address TEXT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE cibil_details (
    cibil_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    cibil_score INT,
    last_updated DATE,
    credit_status VARCHAR(50),
    no_of_enquiries INT,
    no_of_active_accounts INT,
    loan_outstanding DECIMAL(15,2),
    overdue DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
ALTER TABLE cibil_details
    RENAME COLUMN overdue TO emis_total;

