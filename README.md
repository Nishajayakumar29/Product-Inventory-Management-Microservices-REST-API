# Product-Inventory-Management-Microservices-REST-API
# Overview
  This project demonstrates a simple Microservices Architecture with two independent services:

- Product Service (port 8000) – Manages product details (name, price, description, manufacture date, expiry date).

- Inventory Service (port 8082) – Manages stock levels for products and communicates with the Product Service to validate product existence.

- Both services expose REST APIs and exchange data in JSON format. They are designed for easy integration with frontend apps or Postman for testing.

# How It Works
1. Product Service:

- Provides CRUD operations for products: Create, Read, Update, Delete.

- Returns user-friendly responses (e.g., "Product added successfully").

- Supports endpoints to fetch product details like price, description, expiry date, etc.

2. Inventory Service:

- Manages stock levels corresponding to product IDs.

- Uses RestTemplate to validate if a product exists in the Product Service before adding,       updating, or deleting stock.

- If a product is deleted in Product Service, Inventory Service returns a message:
  "The product has been deleted, stock cannot be added/updated/viewed."

* Communication:

The Inventory Service calls the Product Service via REST API to check product validity before any stock operation.

# Purpose
- To showcase Microservices architecture with independent services communicating via REST.

- To practice Spring Boot, REST APIs, CRUD operations, and service-to-service communication.

- To provide a scalable backend system for e-commerce-style product and inventory management.

# Features
a. Product Service:

- Add, update, delete, and view product details.

- Fetch all products, or a single product by ID.

- Fetch product attributes like price, manufacture date, expiry date.

b. Inventory Service:

- Add stock for a valid product.

- View stock (all or for a particular product).

- Update stock levels.

- Delete stock (single or all products).

- Validation with Product Service for product existence.

# Tech Stack
- Backend: Java, Spring Boot

- Database: MySQL (via Spring Data JPA)

- Communication: REST APIs, RestTemplate

- Testing Tool: Postman

- Build Tool: Maven

- Data Format: JSON

