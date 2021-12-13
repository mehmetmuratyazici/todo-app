FROM openjdk:8-jdk-alpine
ADD target/todo-app.jar todo-app.jar
ENTRYPOINT ["java","-jar","/todo-app.jar"]