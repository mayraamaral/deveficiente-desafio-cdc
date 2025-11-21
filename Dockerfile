# Multi-stage build for optimized Spring Boot application

# Build argument for port configuration (can be set in Coolify)
ARG PORT=8080

# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml first for better layer caching
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Make mvnw executable (fix permission issues)
RUN chmod +x ./mvnw

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application (skip tests for faster builds)
RUN ./mvnw clean package -DskipTests -B

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-alpine

# Re-declare ARG after FROM to make it available in this stage
ARG PORT=8080

WORKDIR /app

# Create a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (dynamic based on ARG)
EXPOSE ${PORT}

# Set the server port via environment variable (can still be overridden at runtime)
ENV SERVER_PORT=${PORT}

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
