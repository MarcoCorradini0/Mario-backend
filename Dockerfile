FROM gcr.io/distroless/static
WORKDIR /app
# Scarica sempre l'ultima release nativa
ADD https://github.com/MarcoCorradini0/Mario-backend/releases/latest/download/app /app/app
RUN chmod +x /app/app
EXPOSE 8080
ENTRYPOINT ["/app/app", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}"]