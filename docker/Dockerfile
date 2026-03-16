# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# hadolint ignore=DL3059
COPY pom.xml .
# Качаем зависимости — это закэшируется
RUN mvn dependency:go-offline

COPY src ./src
# Собираем jar
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Копируем собранный jar из stage 1
COPY --from=build /app/target/library-0.0.1-SNAPSHOT.jar app.jar

# Открываем порт приложения
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java","-jar","app.jar"]


