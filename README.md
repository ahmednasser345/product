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

	•	Get All Products (Paginated)

GET /api/products/alll

	•	Parameters: page (default=0), size (default=10)
	•	Response: Paginated list of products

	•	Get All Products (No Pagination)

GET /api/products/all

	•	Response: List of all products

	•	Get Product by ID

GET /api/products/{id}

	•	Response: Product object or 404 if not found

	•	Search Products by Name

GET /api/products/search?name=xyz

	•	Response: List of products matching the search term

Categories

	•	Get All Categories (Paginated)

GET /api/category

	•	Parameters: page, size
	•	Response: Paginated list of categories

	•	Get All Categories (No Pagination)

GET /api/category/all

	•	Response: List of all categories

	•	Get Category by ID

GET /api/category/{id}

	•	Response: Category object or 404 if not found

	•	Search Categories by Name

GET /api/category/search?name=xyz

	•	Response: List of categories matching the search term

Subcategories

	•	Get All Subcategories (Paginated)

GET /api/subcategories

	•	Parameters: page, size
	•	Response: Paginated list of subcategories

	•	Get All Subcategories (No Pagination)

GET /api/subcategories/all

	•	Response: List of all subcategories

	•	Get Subcategory by ID

GET /api/subcategories/{id}

	•	Response: Subcategory object or 404 if not found

	•	Search Subcategories by Name

GET /api/subcategories/search?name=xyz

	•	Response: List of subcategories matching the search term
