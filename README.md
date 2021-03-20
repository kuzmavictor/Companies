# Companies Catalog 
![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![](https://img.shields.io/badge/java_11-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/mysql-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)
![](https://img.shields.io/badge/liquibase-✓-blue.svg)
![](https://img.shields.io/badge/guava-✓-blue.svg)

## External Tools Used
* [Postaman](https://www.getpostman.com/) - API Development Environment (Testing Documentation)

## Prerequisites
- Install JDK11 or higher
- Install MySql 8.0.15 
- Install Maven or use default inside of IDE if it is possible

## Running the application locally
- Create database with name 'catalog'. You may create the database by different ways or execute the following `mysql`command
    ```mysql
  CREATE DATABASE `catalog`;
- Clone the Git repository or download the zip.
- Unzip the zip file (if you downloaded one).
- Change directory (cd) to folder containing pom.xml.

  ```
  cd CompaniesCatalog
  ```
- Run the project. There are several ways to run a Spring Boot application on your local machine. 
  One way is to execute the main method in the `ua.kharkiv.catalog.Application` class from your IDE,
  or use the following command: 
  ```cmd
  mvn spring-boot:run
  ```
  
- Navigate to `http://localhost:8080/swagger-ui.html` in your browser to check everything is working correctly. 
You should see:
