# Product Catalog Backend

This is a Spring Boot backend project that implements a product catalog system with hierarchical categories and subcategories. The project provides CRUD operations, search functionality, and pagination for products, categories, and subcategories.

## Table of Contents
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [API Endpoints](#api-endpoints)

## Getting Started
Follow the instructions below to set up and run the project locally.

### Prerequisites
- Java 23
- Maven
- MySQL

## Project Structure
src/
│   └── main/
│       ├── java/com/example/product/
│       │   ├── config/
│       │   ├── controller/
│       │   ├── model/
│       │   ├── repository/
│       │   └── ProductApplication
│       └── resources/
│           ├── static/
│           ├── templates/
│           └── application.properties
└── test/
    └── java/com/example/product/
        └── controllerTest/
## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ahmednasser345/product.git
   cd product


2. Build the project:

    mvn clean install


## Running the Application

	1.	Start the Spring Boot application:

        Option 1 : mvn spring-boot:run
        Option 2 : run ProductApplication



	2.	Access the API:
	•	The API will be available at: http://localhost:8080

## Running Tests

Run the combined controller tests using Maven:

    mvn test

This will execute the unit and integration tests for all the controllers (Products, Categories, and Subcategories).

## API Endpoints



Products



GET /api/products/alll
	•	Get All Products (Paginated)

	•	Parameters: page (default=0), size (default=10)
	•	Response: Paginated list of products

	

GET /api/products/all
	•	Get All Products (No Pagination)
	•	Response: List of all products

	

GET /api/products/{id}
	•	Get Product by ID
	•	Response: Product object or 404 if not found

	

GET /api/products/search?name=xyz
	•	Search Products by Name

	•	Response: List of products matching the search term

Categories

	

GET /api/category/alll
	•	Get All Categories (Paginated)
	•	Parameters: page, size
	•	Response: Paginated list of categories

	

GET /api/category/all
	•	Get All Categories (No Pagination)
	•	Response: List of all categories

	

GET /api/category/{id}
	•	Get Category by ID
	•	Response: Category object or 404 if not found



GET /api/category/search?name=xyz
	•	Search Categories by Name
	•	Response: List of categories matching the search term

Subcategories

	

GET /api/subcategories/alll
	•	Get All Subcategories (Paginated)
	•	Parameters: page, size
	•	Response: Paginated list of subcategories

	

GET /api/subcategories/all
	•	Get All Subcategories (No Pagination)
	•	Response: List of all subcategories

	

GET /api/subcategories/{id}
	•	Get Subcategory by ID
	•	Response: Subcategory object or 404 if not found

	

GET /api/subcategories/search?name=xyz
	•	Search Subcategories by Name
	•	Response: List of subcategories matching the search term
