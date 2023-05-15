FROM openjdk:17-ea-17-slim-buster
WORKDIR '/app'
ADD build/libs/user-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
COPY . .
ENTRYPOINT ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]