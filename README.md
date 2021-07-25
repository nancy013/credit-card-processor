Credit Card Processor
 
The given repo contains hexagonal architecture based microservice which has two Rest endpoints namely 
- Add a new credit card (POST)
- Fetch all created credit cards(GET).

Run Application
To run application on local
run below command on local: mvn spring-boot:run

Health of the application can be checked by hitting the below end point:
http://localhost:8086/health

Swagger URL:
http://localhost:8086/swagger-ui

Testcases
In order to run test cases, run below command: mvn test

To run single test case, use below command: mvn test --tests <filename>.<testcase>
