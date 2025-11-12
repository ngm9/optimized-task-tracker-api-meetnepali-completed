# stage 1: Build stage
FROM maven:3.9.5-eclipse-temurin-11 AS builder
WORKDIR /app

# Copy only the necessary pom files to leverage Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests -B


# stage 2 runtime stage
FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

#copy the built jar from the builder stage
COPY --from=builder /app/target/task-tracker-0.0.1-SNAPSHOT.jar app.jar

#expose port and run the application
EXPOSE 8080

# Set default JVM memory management options
ENV JAVA_OPTS="-Xms256m -Xmx512m \
    -XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=75.0 \
    -XX:+UseG1GC \
    -XX:MaxGCPauseMillis=100 \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=/tmp/heapdump.hprof \
    -Djava.security.egd=file:/dev/./urandom"

CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
