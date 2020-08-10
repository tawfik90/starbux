# BestSeller Assignment
Hello everyone,

I created this app for a demo, it is not full project because of my time but really I enjoyed it.

Note: The time is really problem because I couldn't finish it in 3 hours, it takes 5 hours at least to finish and test it.

## Intro
This application represents an online coffee place startup, starbux coffee, 
where users can order drinks/toppings and admins can create/update/delete drinks/toppings and have access to reports. 

It will hold all records in H2 database and provide REST API.

my structure
    
    starbux
    |
    |- business
    |   |- domain
    |   |- service
    |- data
    |   |- entity
    |   |- repository
    |- web 
    |   |- congig
    |   |- service
    |   |- exception
    |- exception

   

## The Technology and framework I used as listed here:

* java 8
* Spring Boot
 
## Environment Requirements

1. JDK 1.8
2. Maven 

## How to run the application

You should go to project workspace, It called garage and run this command:

    ./mvnw spring-boot:run 

Or you can run the jar file directly

    java -jar <jar-file-path>

Command lines above will start the app with the default port 8080.

## how to access Swagger documentation

After running the application you can access the API Documentation through this link:
    
    http://localhost:8080/swagger-ui.html

### Dependencies
* Spring
* Lombok
* SwaggerUI
* H2
  
Thanks,

A.Tawfik