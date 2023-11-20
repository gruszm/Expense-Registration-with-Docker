FROM openjdk:17 AS my-app-rest-api
RUN mkdir -p /usr/src/myapprestapi
COPY "./target/my-app-rest-api-1.0.jar" /usr/src/myapprestapi
WORKDIR /usr/src/myapprestapi
CMD ["java", "-jar", "my-app-rest-api-1.0.jar"]