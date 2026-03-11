# Use Maven to build the application
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# copy pom and source code
COPY pom.xml ./
COPY src ./src

# build the jar (skip tests to speed up build)
RUN mvn clean package -DskipTests

# second stage: run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# copy the jar from the build stage
COPY --from=build /app/target/book-api-0.0.1-SNAPSHOT.jar ./book-api.jar

# expose port
EXPOSE 8080

# run the jar
ENTRYPOINT ["java","-jar","/app/book-api.jar"]
