FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

ARG JAR_FILE

# Copy the JAR file into the container at /app/farm-service.jar
COPY target/${JAR_FILE} farm-service.jar

ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:+UseG1GC -XX:MaxMetaspaceSize=256m"

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "farm-service.jar"]