FROM maven:4.0.0-openjdk-20 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -maven.test.skip=true


FROM eclipse-temurin:20-jre-alphine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
