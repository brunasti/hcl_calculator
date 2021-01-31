HCL Assignment
---


# Assignment description

## 1. Simple Calculator – Assessment

During this assessment you will create a Simple Calculator. The goal of this assessment is to judge your development capabilities. The evaluation of your assessment will be based on Agile Software Development Principles, Patterns and Practices and your knowledge on Java, Java frameworks and other development tools you use.


### 1.1. Description

The result of this assessment is to have a calculator backend that can perform calculations. It should have a web-based frontend. E.g. a form in which you can enter a calculation, submit it and display the result. Persist the result in a database. A history of all previous calculations should be shown as well.

Create a "SimpleCalculator"  Java class that will have the following operations:

- public double add(int, int)
- public double subtract(int, int)
- public double multiply(int, int)
- public double divide(int, int)



### 1.2. Guidelines

- Use Test Driven Development to build the "SimpleCalculator" RESTful API - OK
- Use Spring (Boot) for the RESTful API - OK
- Use the Maven build automation tool - OK
- Use Java 11 and coding standards (only JavaDoc for most important parts) - OK
- Use JUnit and Mocking where needed - OK
- Use npm and Angular for the frontend.
- Use git for version control - OK
- Use H2 for database
- Take your time into account. Use 2 dayparts for this assignment and use them to the fullest extent. Try to make the calculator as complete as possible demonstrating that you are familiar with the techniques above, but don’t overcomplicate.


### 1.3. Deliverable

The project(s) containing the code, build instructions and tests. Preferably posted in a repository in e.g. Github.



# Design


# Implementation

## Create Spring Boot skeleton

    https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.4.2.RELEASE&packaging=war&jvmVersion=11&groupId=it.brunasti.hcl&artifactId=calculator&name=Calculator&description=HCL%20Calculator%20Coding%20Assignment&packageName=it.brunasti.hcl.calculator&dependencies=devtools,lombok,configuration-processor,data-jpa,restdocs,web,h2

