FROM maven:3.6.3-openjdk-15-slim AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:15-jdk-alpine

EXPOSE 8080

# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
COPY --from=MAVEN_BUILD /target/demo-0.0.1-SNAPSHOT.jar /demo.jar

ENTRYPOINT ["java","-jar","/demo.jar"]