-- Create database
CREATE DATABASE department_store;
USE department_store;

-- Users table (for all system users)
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    address TEXT,
    user_type ENUM('CUSTOMER', 'SALE_STAFF', 'MANAGEMENT_STAFF') NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

-- Suppliers table
CREATE TABLE suppliers (
    supplier_id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_name VARCHAR(100) NOT NULL,
    contact_info TEXT,
    address TEXT,
    is_active BOOLEAN DEFAULT TRUE
);

-- Products/Items table
CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    category VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    quantity_in_stock INT DEFAULT 0,
    image_url VARCHAR(500),
    supplier_id INT,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

-- Orders table
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL,
    status ENUM('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED') DEFAULT 'PENDING',
    FOREIGN KEY (customer_id) REFERENCES users(user_id)
);

-- Order details table
CREATE TABLE order_details (
    order_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Invoices table
CREATE TABLE invoices (
    invoice_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_status ENUM('PENDING', 'PAID', 'FAILED') DEFAULT 'PENDING',
    total_amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

-- Imports table
CREATE TABLE imports (
    import_id INT PRIMARY KEY AUTO_INCREMENT,
    import_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    supplier_id INT NOT NULL,
    total_cost DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

-- Import details table
CREATE TABLE import_details (
    import_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    import_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_cost DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (import_id) REFERENCES imports(import_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Insert sample data
INSERT INTO users (username, password, full_name, email, phone_number, address, user_type) VALUES
('customer1', 'password123', 'Nguyen Van A', 'customer1@email.com', '0123456789', 'Hanoi', 'CUSTOMER'),
('sale1', 'password123', 'Tran Thi B', 'sale1@email.com', '0987654321', 'Hanoi', 'SALE_STAFF'),
('manager1', 'password123', 'Le Van C', 'manager1@email.com', '0912345678', 'Hanoi', 'MANAGEMENT_STAFF');

INSERT INTO suppliers (supplier_name, contact_info, address) VALUES
('Supplier A', 'contact@suppliera.com', 'Hanoi'),
('Supplier B', 'contact@supplierb.com', 'Ho Chi Minh City');

INSERT INTO products (name, description, category, price, quantity_in_stock, supplier_id) VALUES
('Wireless Mouse', 'High-quality wireless mouse', 'Electronics', 25.99, 100, 1),
('Mechanical Keyboard', 'Gaming mechanical keyboard', 'Electronics', 89.99, 50, 1),
('Laptop Stand', 'Adjustable laptop stand', 'Accessories', 35.50, 75, 2),
('USB-C Cable', 'Fast charging USB-C cable', 'Electronics', 15.99, 200, 2);