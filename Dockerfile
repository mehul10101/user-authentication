FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/uberAuthentication-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} userAuth.jar
ENTRYPOINT ["java","-jar","userAuth.jar"]