FROM openjdk:19-jdk-alpine
COPY target/appV1-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "appV1-0.0.1-SNAPSHOT.jar"]