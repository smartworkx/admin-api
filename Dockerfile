FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/app.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]