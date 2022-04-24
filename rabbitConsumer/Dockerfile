FROM openjdk:14-alpine3.10
COPY ./build/libs/rabbitConsumer.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]