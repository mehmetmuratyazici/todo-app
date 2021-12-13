FROM openjdk:8-jdk-alpine
ADD target/todo-app.jar todo-app.jar
EXPOSE 8099
ENTRYPOINT ["java","-jar","/todo-app.jar"]