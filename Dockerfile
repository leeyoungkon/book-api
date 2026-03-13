# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /build

# pom 먼저 복사 (dependency 캐시 활용)
COPY pom.xml .

RUN mvn -B dependency:go-offline

# 소스 복사
COPY src ./src

# 테스트 스킵하고 빌드
RUN mvn -B clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# jar 복사
COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]