FROM java:8-alpine

COPY ./target/AutoGeneralTestAPI-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT $JAVA_HOME/bin/java -jar app.jar $APP_OPTS