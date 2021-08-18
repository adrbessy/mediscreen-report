<img src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white"/> * * *  <img src="https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/>  * * *  <img src="https://img.shields.io/badge/docker%20-%230db7ed.svg?&style=for-the-badge&logo=docker&logoColor=white"/>

# Mediscreen
An application to predict risks of diseases.
This app uses Java to run.


### Architecture diagram
![Alt text](doc/mediscreen_architecture_diagram.png?raw=true "Architecture diagram of Mediscreen")


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 4.0.0

### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

### Running App

You can run the application in two different ways:

1/ import the code into an IDE of your choice and run the Application.java to launch the application.

2/ Or import the code, unzip it, open the console, go to the folder that contains the pom.xml file, then execute the below command to launch the application.

```bash
mvn spring-boot:run 
```

### Docker deploiement

Generate a jar file for each java microservice with:

```bash
mvn package
```


Then go to the folder that contains the Dockerfile, and then to build an image type:

```bash
docker build -t mediscreen-patient:0.0.1 .
```

Then go to the respective folder of each microservice and to build the images type:

```bash
docker build -t mediscreen-angular-ui:0.0.1 .
docker build -t mediscreen-note:0.0.1 .
docker build -t mediscreen-report:0.0.1 .
```

Build the image of postgres:

```bash
docker build -t postgres:latest
```


Then to deploy all mediscreen microservices, type :

```bash
docker-compose up -d
```


Tests to enter note data in database:

```bash
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "1", "note": "Patient states that they are feeling terrific Weight at or below recommended level"}'
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "2", "note": "Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late"}'
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "2", "note": "Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic"}'
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "3", "note": "Patient states that they are short term Smoker "}'
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "3", "note": "Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL high"}'
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "4", "note": "Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication"}'
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "4", "note": "Patient states that they are experiencing back pain when seated for a long time"}'
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "4", "note": "Patient states that they are a short term Smoker Hemoglobin A1C above recommended level"}'
curl -X POST -H "Content-Type: application/json" http://localhost:9011/note -d '{"patientId": "4", "note": "Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction"}'
```


### API calls (URI, parameters)
GET

http://localhost:9010/patients

http://localhost:9010/patient?id=1

http://localhost:9011/notes?patientId=2

http://localhost:9012/assess?patientId=41

http://localhost:9012/assess/Test


POST

http://localhost:9010/patient

http://localhost:9011/note


PUT

http://localhost:9010/patient/4

http://localhost:9011/note/61092a7d6d70d9092b1de5be


DEL

http://localhost:9010/patient?id=12

http://localhost:9011/note?id=6109914530a2995c1937e848


### Testing
The app has unit tests written.

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

```bash
mvn test
```

### UT jacoco coverage
![Alt text](doc/UT-jacoco-coverage.png?raw=true "Couverture Jacoco de Tests Unitaires")