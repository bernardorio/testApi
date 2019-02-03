Auto&General Test API Exercise
==============================

Built as an exercise, this API should mimic the behaviour of the TestExercise API built by Auto&General, and exposed via its SwaggerUI interface

## Requirements

- If you are running it locally, you need the following installed:
    Java - 1.8+, Maven 3+.
- If you decide to use the 'dockerised' version, all you need is Docker installed and running

## Considerations:

- No extra-ordinary feature (such as security, actuactor, etc)
- In memory database was used.

## Running it:

1. Clone the app, and cd into the directory
```
git clone
cd AutoGeneralTestAPI
```
2. Run your application
```
mvn spring-boot:run
```
Alternatively, the following command will build a snapshot, build a docker image and start it
```
./build_image_run.sh
```
 
3. The application should be running now, and you can interact with it. You may access the SwaggerUI endpoint on
```
http://localhost:8080/swagger-ui.html
```

# Endpoints

There are 2 resources available, /todo and /task. 
  
  - /todo resource implements the GET, POST and PATCH verbs
  - /task resource is comprised by a single endpoint that validates a sequence of brackets, eg '{[]}' being valid, and '{[}]' not.
  
For more information, and the Model accepted by the endpoints, please refer to the Swagger api, exposed on /swagger-ui.html
