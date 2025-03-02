# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/WebsiteRoomForRent-0.0.1-SNAPSHOT.jar app.jar

# Copy the images folder into the container
COPY images /app/images

# Expose the port the application runs on
EXPOSE 8080

# Set environment variables for MySQL connection on localhost
ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/websiteroomforrent
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Baomax1001.

# Wait for MySQL to be ready (optional but recommended)
HEALTHCHECK --interval=10s --timeout=3s --start-period=30s CMD nc -z host.docker.internal 3306 || exit 1

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]