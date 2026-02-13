# ===== BUILD STAGE =====
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ===== RUN STAGE =====
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia l'app già buildata
COPY --from=build /app/target/quarkus-app /app

# Profile Quarkus: prod
ENV QUARKUS_PROFILE=prod

# Espone porta 8080
EXPOSE 8080

# Avvia Quarkus con profilo prod
CMD ["sh", "-c", "java -jar quarkus-run.jar -Dquarkus.profile=${QUARKUS_PROFILE} -Dquarkus.http.port=${PORT}"]
