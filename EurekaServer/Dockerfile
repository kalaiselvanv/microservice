FROM openjdk:11 as build
ADD target/EurekaServer-0.0.1.jar EurekaServer-0.0.1.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "EurekaServer-0.0.1.jar"]