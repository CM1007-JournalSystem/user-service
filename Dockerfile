# Stage 1: Build the application (using Maven)
FROM maven:3.8.8-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy only necessary files for the build
COPY pom.xml ./
COPY src ./src

# Run Maven build
RUN mvn clean package -DskipTests

# Stage 2: Prepare the final runtime image
FROM eclipse-temurin:17-jdk AS runtime

ENV LANGUAGE='en_US:en'
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod"

# Create a non-root user to run the application securely
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Expose necessary ports
EXPOSE 8080

# Set entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]