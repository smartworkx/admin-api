FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD dist/admin-api.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]