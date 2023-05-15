FROM openjdk:17-ea-17-slim-buster
WORKDIR '/app'
ADD build/libs/1-service-discovery-0.0.1-SNAPSHOT.jar .
EXPOSE 8761
COPY . .
ENTRYPOINT ["java", "-jar", "1-service-discovery-0.0.1-SNAPSHOT.jar"]