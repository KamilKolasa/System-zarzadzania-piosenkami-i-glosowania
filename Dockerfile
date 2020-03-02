FROM openjdk:11-oracle
EXPOSE 8081

WORKDIR /app

ARG JAR_FILE=app.jar

ADD ./target/${JAR_FILE} ${JAR_FILE}

ENTRYPOINT ["java", "-jar", "app.jar"]

# 1. zbuduj projekt
# >> mvn clean package