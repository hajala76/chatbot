FROM openjdk:16

RUN mkdir -p /app
WORKDIR /app
COPY target/*.jar ./app.jar
EXPOSE $PORT
CMD [ "java", "-jar", "./app.jar" ]