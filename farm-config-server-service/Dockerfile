FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} config-server.jar

# Set the JAVA_OPTS environment variable to include the required JVM options
ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:+UseG1GC -XX:MaxMetaspaceSize=256m"

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "config-server.jar"]