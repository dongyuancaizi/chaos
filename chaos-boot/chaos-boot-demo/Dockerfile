FROM openjdk:8-alpine
VOLUME /tmp
ADD target/qitianzhen-service-2.1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
