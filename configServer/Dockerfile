FROM openjdk:17
ARG JAR_FILE=target/configServer.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8888