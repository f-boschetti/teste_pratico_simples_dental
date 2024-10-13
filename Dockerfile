FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/simplesDental-0.0.1-SNAPSHOT.jar simplesDental.jar
ENTRYPOINT ["java", "-jar", "simplesDental.jar"]