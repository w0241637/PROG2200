FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/Spring01_REST-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]