Auto&General Test API Exercise
==============================

This project was an implementation of the Swagger documented API provided 

## Requirements

- If you are running it locally, you need the following installed:
    Java - 1.8+, Maven 3+.
- If you decide to use the dockerised version, all you need is Docker installed and running

## Considerations:

- Since I believe this was the purpose of the exercise, I kept it simple (ie. didn't add any unnecessary feature, such as secure endpoints, actuator, etc); 
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

