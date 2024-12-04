# Använd en slimmad OpenJDK-bild
FROM openjdk:17-jdk-slim

WORKDIR /app

# Kopiera projektfiler och bygg
COPY . .
RUN ./mvnw package -DskipTests

# Exponera porten för tjänsten
EXPOSE 8081

CMD ["java", "-jar", "/app/target/user-service-0.0.1-SNAPSHOT.jar"]
