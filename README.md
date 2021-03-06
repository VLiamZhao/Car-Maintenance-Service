# Car Maintenance Project by Can Zhao
## Description 
A Spring Boot and Hibernate based Car Maintenance Service Web Application
1. Built a car maintenance web service by using Spring Boot and Web Flow with functionalities including service listing, searching, and ordering
2. Implemented JWT Stateless Authentication security workflow
3. Used Docker PostgreSQL image and Tomcat image to ensure the isolation of each module
4. Implemented a consumer microservice to consume messages by using AWS SQS
5. Deployed the application on the Cloud by using AWS ECR web service
6. Tested and Deployed the application through CI/CD pipeline.
## Assumption
1. Users can check or edit personal informations and car maintenance service information based on the authorities.
2. The information is created before searching.
3. Users can add maintenance services for their cars.
## Approach
* Project Business Rules:
1. Object: Car, Maintenance, Customer, Role
2. Relationships:
    1. One customer can have many cars.
    2. One car can have several maintenance services.
    3. One customer can have multiple roles.
* Project Approach:

    1. Create Car, Maintenance, Customer, Role.
    2. Used Hibernate to do the database schema migration.
    3. Used JDBC to connect project with Postgres.
    4. Configured Spring Security for Authentication.
    5. Created repository, service and did test.
    6. Created Controllers and Restful APIs.
    7. Used mockito library to test AWS S3 Storage service.
    8. Integrated third-party application AWS SQS and tested by Mockito.
 
    

## Build Project
1. Clone the project.
```
git clone https://github.com/VLiamZhao/Car-Maintenance-Service.git
```
2. Install Docker if necessary.
3. Use command window to spin up the PostgreSQL database server using Postgres docker image.
```
docker pull postgres

docker run --name ${PostgresContainerName} -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p ${hostport}:${containerport} -d postgres
```
4. Create Unit database on PGAdmin for unit testing
```
create database Maintenance_Service;
```
5. Environment properties configuration
```
location:./src/main/resources/META-INF/env
   
Template:
database.driver=${driver}
database.url=${url}
database.port=${port}
database.name=${name}
database.username=${username}
database.password=${password}
   
mvn compile -Dspring.profiles.active=${env}
```
6. Schema Flyway migration for creating tables in database
```
mvn compile flyway:migrate -P unit -Ddb_username=${username} -Ddb_url=localhost:${containerport}/${databasename} -Ddb_password=${password} 
```

## Test
- Package and install the folder before unit test 
```
mvn clean compile install -DskipTests=true
```
-Run the test with the command. All the Test are done using JUnit and Mockito
```
mvn compile test -Dspring.profiles.active=${unit} -Daws.region=${region} -Ddb_url=${localhost:5432/pigge_unit} -Ddb.username=${username} -Ddb.password=${password} 
```

## Packaging
```
mvn clean compile package -DskipTests=true
```
## API guideline and Reference DEMO
- You need to sign up for The authority to get access.<br />
Send a GET request in this address to create a new account.<br />
     
```
GET - http://localhost:8080/auth/registration
```
Put the request body.
```
{
	"name": "Test",
	"email": "test@gmail.com",
	"password": "123"
}

``` 
The response would be like:
```
{
    "Email": "test@gmail.com",
    "Id": "15",
    "Name": "Test"
}

```
DEMO screen shoot:
![Image of signUp](https://github.com/VLiamZhao/Car-Maintenance-Service/blob/master/web/src/main/resources/car-test.png?raw=true)
- You need to login.<br />
Send a post request to this url.
```
POST - http://localhost:8080/auth
```
Edit the request body.（ You can chose login with username or email)
```
{
    "email": "test@gmail.com",
    "password": "123"
}
``` 
Then the response will be like:
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNSIsInN1YiI6IlRlc3QiLCJpYXQiOjE1OTI2NDM4NzAsImlzcyI6Im9yZy5teW5vZGUiLCJleHAiOjE1OTI3MzAyNzAsImFsbG93ZWRSZXNvdXJjZSI6Ii9jYXIsIC9tYWludGVuYW5jZSIsImFsbG93ZWRSZWFkUmVzb3VyY2VzIjoiL2NhciwgL21haW50ZW5hbmNlIiwiYWxsb3dlZENyZWF0ZVJlc291cmNlcyI6IiIsImFsbG93ZWRVcGRhdGVSZXNvdXJjZXMiOiIiLCJhbGxvd2VkRGVsZXRlUmVzb3VyY2VzIjoiIn0.Ky6EX7h4gz9yw1DdJhIqijFKlyACzJCJvhgcfkhvjYU"    
}
```
This token is needed for the future access to other api. So you don't have to login to the every API.
# CI/CD

You should have completed the following stages before you work with DevOps engineer.

  1. Upload source code to GitHub repository
  2. Fulfill unit test stage in docker container
  3. Package **.war** file in docker container
  4. Build Docker image with **.war** file and Dockerfile
  5. Launch containerized application successfully

## GitHub

Make sure the source code in the github is the latest(runnable) version.   

***IMPORTANT: DO NOT INCLUDE ANY CREDENTIAL IN THE CODE.***

## Unit Test
>Use `Docker` to pull `Maven` image and run an interactive container.
>
    docker pull maven:3.6.0-jdk-8
    docker run -it maven:3.6.0-jdk-8 /bin/bash

>Use `Git` to retrieve source code from `GitHub`.
>
    git clone <repository_url>
    
>Get into the project's folder, then use `Flyway` to migrate data.
>
    mvn clean compile flyway:migrate -Ddatabase.url=jdbc:postgresql://${database_url}:5432/${database_name} 
    -Ddatabase.user=${user_name} -Ddatabase.password=${password}
    
Notice: We are currently running in the container. Thus, the database connection is no longer localhost:5432.
You should inspect `postgreSQL` server container to find the IP address. Find the internal IP address of the container by using:
    
    docker inspect ${container_id} | grep "IPAddress"
    
>Run unit tests in the container.
>
    mvn test -Ddatabase.url=jdbc:postgresql://${database_url}:5432/${database_name} -Dspring.profiles.active=unit -Ddatabase.user=${user_name} 
    -Ddatabase.password=${password} -Daws.accessKeyId=${access_key} -Daws.secretKey=${secret_key} 
    -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.driver=org.postgresql.Driver
