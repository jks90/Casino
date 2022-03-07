FROM openjdk
WORKDIR /
ADD target/casino-backend-1.0.0-SNAPSHOT.jar casino.jar
EXPOSE 8080
CMD ["java", "-jar", "casino.jar"]