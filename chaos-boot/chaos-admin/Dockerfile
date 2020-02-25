FROM java:8
VOLUME /tmp
ADD target/chaos-admin-1.0.0-RELEASE.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
