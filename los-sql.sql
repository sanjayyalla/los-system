-- drop database los;  
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
    address TEXT,
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
    status ENUM('Pending', 'Accepted', 'Rejected', 'In Process'),
    approval_amount DECIMAL(15,2),
    is_active BOOLEAN,
    loan_term_in_months INT,
    loan_purpose varchar(255),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
