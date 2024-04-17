FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} client.jar

# Set the JAVA_OPTS environment variable to include the required JVM options
ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:+UseG1GC -XX:MaxMetaspaceSize=256m"

EXPOSE 8001

# Run the API Gateway service using the jar file
ENTRYPOINT ["java", "-jar", "client.jar"]