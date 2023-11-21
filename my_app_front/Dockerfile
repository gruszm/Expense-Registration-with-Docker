FROM openjdk:17 AS my-app-front
RUN mkdir -p /usr/src/myappfront
COPY "./target/my-app-front-1.0.jar" /usr/src/myappfront
WORKDIR /usr/src/myappfront
CMD ["java", "-jar", "my-app-front-1.0.jar"]