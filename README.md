# Credit Card Processor
 
The given repo contains ***hexagonal architecture*** based microservice which has two rest endpoints namely 
- Add a new credit card (POST)
- Fetch all created credit cards(GET).

## Run Application
To run application on local: *mvn spring-boot:run*

Health Url:
http://localhost:8086/health

Swagger Url:
http://localhost:8086/swagger-ui/

## Testcases
In order to run test cases, run below command: *mvn test*

## Docker
Command to create image:
*docker build -f Dockerfile -t creditcardprocessor .*

Command to the run the image:
*docker run -p 8086:8086 creditcardprocessor*

To access the service use below url:
http://localhost:8086/swagger-ui/

