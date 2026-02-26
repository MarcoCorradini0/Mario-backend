FROM gcr.io/distroless/static
WORKDIR /app
ADD https://raw.githubusercontent.com/MarcoCorradini0/Mario-backend/main/native-binaries/latest/app /app/app
RUN chmod +x /app/app
EXPOSE 8080
ENTRYPOINT ["/app/app", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}"]