FROM openjdk:8
ADD target/mediscreen-0.0.1-SNAPSHOT.jar mediscreen-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/mediscreen-0.0.1-SNAPSHOT.jar"]
