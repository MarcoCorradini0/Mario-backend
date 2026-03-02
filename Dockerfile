# ---------- STAGE 1: scarica asset ----------
    FROM alpine:3.19 AS fetch
    WORKDIR /tmp
    RUN apk add --no-cache curl
    ARG APP_URL=https://github.com/MarcoCorradini0/Mario-backend/releases/latest/download/app
    RUN curl -fL "$APP_URL" -o app && chmod +x app
    
# ---------- STAGE 1b: prendi libz.so.1 da debian slim ----------
    FROM debian:bookworm-slim AS libs
    RUN apt-get update && apt-get install -y --no-install-recommends zlib1g && \
        rm -rf /var/lib/apt/lists/*
    
# ---------- STAGE 2: runtime distroless cc ----------
    FROM gcr.io/distroless/cc-debian12
    WORKDIR /app
    # copia il binario
    COPY --from=fetch /tmp/app /app/app
    # copia la libreria zlib dinamica dove il linker se l’aspetta
    COPY --from=libs /usr/lib/x86_64-linux-gnu/libz.so.1 /usr/lib/x86_64-linux-gnu/libz.so.1
    
    EXPOSE 8080
    ENTRYPOINT ["/app/app", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}"]