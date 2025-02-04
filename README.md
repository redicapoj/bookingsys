# Booking System

This is a simple booking system form application developed using **Java EE** with **HTML**, **CSS**, and **JavaScript** for the frontend.

## Features
- Add client and reservation
- Room and reservation management
- Integration with MySQL database
- Backend built with Java EE Servlets and JDBC

## Requirements
- Java 17
- Apache Tomcat 9 or higher
- MySQL 8.0
- Maven 3.6 or higher

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/redicapoj/bookingsys.git
2. Navigate to the project directory:
   ```bash
   cd bookingsys
3. Build the project using Maven:
   ```bash
   mvn clean package
4. Deploy the WAR file (target/bookingsys.war) to Tomcat.

## Database Setup
1. Create a MySQL database named booking_system.
2. Run the SQL script provided in src/main/resources/schema.sql to set up the schema.
3. Update the database connection details in DatabaseConnection.java:
   ```java
   String url = "jdbc:mysql://localhost:3306/booking_system";
   String username = "your_username";
   String password = "your_password";
   
## Running the application
1. Start Tomcat
2. Access the application at http://localhost:8080/bookingsys.

## Contributing
Feel free to fork the project, create feature branches, and open pull requests.
