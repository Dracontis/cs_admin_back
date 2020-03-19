# TODO: just an example, should be tuned later
FROM openjdk:8-jdk-alpine
VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
EXPOSE 8080