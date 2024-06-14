FROM maven AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/ASN2-0.0.1-SNAPSHOT.jar ASN2.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ASN2.jar"]