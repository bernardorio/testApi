package au.com.autogeneral.AutoGeneralTestAPI.config;

import au.com.autogeneral.AutoGeneralTestAPI.TestApiApplication;
import au.com.autogeneral.AutoGeneralTestAPI.error.ToDoItemNotFoundError;
import au.com.autogeneral.AutoGeneralTestAPI.error.ToDoItemValidationError;
import org.slf4j.Logger;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.toString;
import static java.util.stream.Collectors.joining;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(basePackageClasses=TestApiApplication.class)
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = getLogger(RestResponseEntityExceptionHandler.class);

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ToDoItemNotFoundError> resourceNotFoundExceptionExceptionHandler(ResourceNotFoundException exception) {
        logger.info("Attempt to retrieve invalid resource: {}", exception.getMessage(), exception);
        ToDoItemNotFoundError exceptionResponse = new ToDoItemNotFoundError(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info("Input validation failed. Detailed message: {}", exception.getMessage(), exception);
        List<ToDoItemValidationError.ErrorDetails> errorDetails = new ArrayList<>();
        for (FieldError ex : exception.getBindingResult().getFieldErrors() ) {
            errorDetails.add(new ToDoItemValidationError.ErrorDetails("params", ex.getField(), ex.getDefaultMessage(), ""));
        }
        ToDoItemValidationError exceptionResponse = new ToDoItemValidationError(errorDetails);
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ToDoItemValidationError> handleConstraintViolationException(ConstraintViolationException exception) {
        logger.info("Input validation failed. Detailed message: {}", exception.getMessage(), exception);
        ToDoItemValidationError.ErrorDetails errorDetails = new ToDoItemValidationError.ErrorDetails("params","text", collectErrorMessages(exception),"");
        ToDoItemValidationError exceptionResponse = new ToDoItemValidationError(asList(errorDetails));
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    private String collectErrorMessages(final ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(
                constraintViolation -> constraintViolation.getMessage()).collect(joining(", "));
    }

}