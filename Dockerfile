FROM openjdk:8-jdk-alpine
LABEL author="code.immanuelqrw@gmail.com"
VOLUME /tmp
ADD /build/libs/*.jar ika.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ika.jar"]
