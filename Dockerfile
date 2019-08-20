FROM openjdk:8

RUN mkdir /app

ADD target/dictionary_api.jar /app/dictionary_api.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "dictionary_api.jar"]