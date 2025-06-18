FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/auth-service.jar auth-service.jar
ENTRYPOINT ["java","-jar","/auth-service.jar"]