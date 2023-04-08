FROM openjdk:11-slim AS build

WORKDIR /app

COPY ./mvnw ./
COPY ./.mvn ./.mvn

COPY ./pom.xml .
RUN chmod 755 /app/mvnw 
RUN ./mvnw dependency:go-offline -B

COPY ./src ./src
RUN ./mvnw package -DskipTests

EXPOSE 8080

FROM build

COPY --from=build /app/target /app

ENTRYPOINT ["java", "-jar", "user-0.0.1-SNAPSHOT.jar"]
