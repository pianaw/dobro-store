FROM openjdk:11-jre
WORKDIR /app

COPY target/dependency /app/dependency
COPY target/ds-main-service-1.0.jar /app/

HEALTHCHECK --interval=10s --timeout=10s --start-period=30s --retries=1 CMD curl -f http://localhost:${MAIN_SERVICE_PORT}/actuator/health || exit 1
ENTRYPOINT ["java", "-cp", "./*:./dependency/*", "org.springframework.boot.loader.JarLauncher"]
