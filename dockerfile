FROM openjdk:11
LABEL maintainer="com.deepsense"
ADD target/spring-boot-demo-0.0.1-SNAPSHOT.jar springboot-docker-demo.jar
ENTRYPOINT ["java", "-jar", "springboot-docker-demo.jar"]