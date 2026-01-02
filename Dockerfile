FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /userapp

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre-alpine

WORKDIR /userapp

COPY --from=build /userapp/target/*.jar userapp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "userapp.jar"]

