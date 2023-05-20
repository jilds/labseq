FROM maven:3.9.2-amazoncorretto-17 AS maven
WORKDIR /tmp
COPY . .
RUN mvn clean package

FROM amazoncorretto:17.0.7-alpine3.16
VOLUME /tmp
ARG JAR_FILE=target/labseq.jar
ADD ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]