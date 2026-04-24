# Use a lightweight Alpine-based Java 25 image
# FROM eclipse-temurin:25-jdk-alpine as build
FROM maven:3.9.11-eclipse-temurin-25-alpine as build
WORKDIR /app


# Copy the entire project into the container
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Use a smaller runtime image for the final stage
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point for the container
# ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
ENTRYPOINT ["java", "-jar", "app.jar"]

