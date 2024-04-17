FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} eureka.jar

# Set the JAVA_OPTS environment variable to include the required JVM options
ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:+UseG1GC -XX:MaxMetaspaceSize=256m"

# Expose port 8761 for the eureka service
EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eureka.jar"]