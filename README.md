📝 Todo List Application – Backend

A RESTful Todo List backend application built using Java, Spring Boot, and Maven.
This project demonstrates CRUD operations, layered architecture, and REST API development.

✅ Completed up to Version 5

🚀 Tech Stack

Java 17

Spring Boot 3.x

Maven

Spring Web

Spring Data JPA

H2 / MySQL (update if you're using a specific database)

REST API Architecture

📌 Features (Up to Version 5)

✅ Create a new Todo

✅ Get all Todos

✅ Get Todo by ID

✅ Update Todo

✅ Delete Todo

✅ RESTful API design

✅ Layered architecture (Controller, Service, Repository)

✅ Clean project structure

✅ Maven-based dependency management

🏗️ Project Architecture

The project follows a standard layered architecture:

Controller → Service → Repository → Database
📂 Project Structure
backend
 ├── src/main/java/com/todoapp/backend
 │     ├── controller
 │     ├── service
 │     ├── repository
 │     ├── model
 │     └── BackendApplication.java
 │
 ├── src/main/resources
 │     └── application.properties
 │
 └── pom.xml
⚙️ How to Run the Project
1️⃣ Clone the Repository
git clone https://github.com/your-username/your-repo-name.git
cd backend
2️⃣ Build the Project
mvn clean install
3️⃣ Run the Application
mvn spring-boot:run
4️⃣ Access the Application

The server will start at:

http://localhost:8080
📮 API Endpoints
Method	Endpoint	Description
GET	/todos	Get all todos
GET	/todos/{id}	Get todo by ID
POST	/todos	Create a new todo
PUT	/todos/{id}	Update an existing todo
DELETE	/todos/{id}	Delete a todo
🧪 Example JSON Request
Create Todo (POST /todos)
{
  "title": "Complete Spring Boot project",
  "description": "Finish backend development",
  "completed": false
}
🧪 Testing the APIs

You can test the APIs using:

Postman

Thunder Client (VS Code extension)

curl

Browser (for GET requests)

🛠️ Maven Commands Used
Command	Purpose
mvn clean install	Build the project
mvn spring-boot:run	Run the application
mvn test	Run test cases
🔄 Version History
Version 1

Basic Spring Boot setup

Version 2

Added Todo Entity and Repository

Version 3

Implemented CRUD operations

Version 4

Added Service Layer

Version 5

Improved project structure

Clean API design

Bug fixes and improvements

🚀 Future Improvements

🔐 Add Authentication (JWT / Spring Security)

🗄️ Connect to MySQL / PostgreSQL

📄 Add Pagination & Sorting

🌐 Integrate Frontend (React / Angular)

🐳 Dockerize the application

🧪 Add Unit & Integration Tests
