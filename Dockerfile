# ---------- STAGE 1: scarica l'asset e rendilo eseguibile ----------
    FROM alpine:3.19 AS fetch
    WORKDIR /tmp
    RUN apk add --no-cache curl
    # URL dell'asset 'app' dalla Release 'native-latest'
    ARG APP_URL=https://github.com/MarcoCorradini0/Mario-backend/releases/latest/download/app
    RUN curl -fL "$APP_URL" -o app && chmod +x app
    
# ---------- STAGE 2: runtime distroless static ----------
    FROM gcr.io/distroless/static
    WORKDIR /app
    COPY --from=fetch /tmp/app /app/app
    EXPOSE 8080
    ENTRYPOINT ["/app/app", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}"]