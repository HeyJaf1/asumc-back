# Stage 1: Build (context = project root)
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom files for dependency caching
COPY pom.xml .
COPY asumc-common/pom.xml asumc-common/
COPY asumc-dto/pom.xml asumc-dto/
COPY asumc-storage/pom.xml asumc-storage/
COPY asumc-core/pom.xml asumc-core/

RUN mvn dependency:go-offline -B

# Copy sources
COPY asumc-common/src asumc-common/src
COPY asumc-dto/src asumc-dto/src
COPY asumc-storage/src asumc-storage/src
COPY asumc-core/src asumc-core/src

RUN mvn package -DskipTests -B

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine AS runtime

# Non-root user
RUN addgroup -S asumc && adduser -S asumc -G asumc

WORKDIR /app

COPY --from=build /app/asumc-core/target/asumc-core-1.0.0.jar app.jar

RUN chown asumc:asumc app.jar

USER asumc

EXPOSE 8080

ENTRYPOINT ["java", \
  "-server", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-jar", "app.jar"]
