# ArticleService
A Spring Boot application for managing and reporting articles based on their URLs, social scores, and country codes.

Features
- Add and delete articles
- Fetch reports based on country codes
- RESTful API with Spring Boot
- MySQL as the database
- JUnit & Mockito for testing

Prerequisites
Before running the application, ensure you have the following installed:

1. Required Software
- Java 8 or later
- Maven
- MySQL
- Postman for testing APIs)
- Eclipse/IntelliJ IDE for editing code

2. Install Java & Maven
Windows:
Download and install:
- Java JDK 8+(https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
- Apache Maven(https://maven.apache.org/download.cgi)
- MySQL (https://dev.mysql.com/downloads/installer/)
- Eclipse (https://www.eclipse.org/downloads/)
- Postman (https://www.postman.com/downloads/)

Verify installations: Open cmd and run the below commands
java -version
mvn -version

Setting Up MySQL Database:

1. Run MySQL (Installed MySQL)
If MySQL is installed on your system, start the MySQL service and create a database: Open cmd and run the below commands

mysql -u root -p
CREATE DATABASE article_db;

Configuration
Modify the `application.properties` file inside `src/main/resources/`:
spring.datasource.url=jdbc:mysql://localhost:3306/article_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=[Your_DB_RootUserName]
spring.datasource.password=[Your_DB_Password]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

APIs in the application:
To add a URL with an associated social score and country code- http://localhost:8080/api/article
To delete URL and the related data from the system - http://localhost:8080/api/article?url={url}
To get aggregated social score for the domains/vountry codes in the system ranked by social score - http://localhost:8080/api/report/ie

Running the Application

Open the code in Eclipse->Right-click on 'article-service' project->Maven Cleean->Maven Install->Run the 'article-service' as a spring boot application
OR
Navigate to the project's root directory and execute: run the below commands in cmd
mvn spring-boot:run

The application should start at: `http://localhost:8080`

Testing the API
Use Postman or cURL to test API endpoints.

1. Add an Article
Operation : POST 
API endpoint : http://localhost:8080/api/article
Content-Type : JSON
Payload -
  {
  "url": "http://example.com",
  "socialScore": -5,
  "countryCode": "us"
}


2. Delete an Article
Operation : POST 
API endpoint : http://localhost:8080/api/article?url={url} 
Replace {url} with the url to be deleted.
Example : http://localhost:8080/api/article?url=https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/


3. Get Report by Country
Operation : GET 
API endpoint : http://localhost:8080/api/report/{country_code}
Replace {country_code} with the country_code to be deleted.
Content-Type : JSON


Running Unit Tests
To execute tests, run: Navigate to the root directory of the code and run the below commands in cmd
mvn test

Right-click on 'article-service' in Eclipse IDE->Run As Mavan test

Right-click on 'article-service' in Eclipse IDE->Run As JUnit Test


Tests include:
- Controller tests using Mockito
- Service layer tests
