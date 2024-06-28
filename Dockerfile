FROM maven:3.9.6 AS build

WORKDIR /aop

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -DskipTests

FROM openjdk:21

WORKDIR /aop

COPY --from=build /aop/target/*.jar aop.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "aop.jar"]