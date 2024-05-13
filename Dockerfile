FROM maven:3.8.4-openjdk-8 AS build
COPY . .
RUN mvn clean package -DskipTest

FROM openjdk:8-jdk-slim
COPY --from=build /target/ticket-0.0.1-SNAPSHOT.jar ticket.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ticket.jar"]