package au.com.autogeneral.testAPI.config;

import au.com.autogeneral.testAPI.controllers.TodoController;
import au.com.autogeneral.testAPI.error.ToDoItemNotFoundError;
import au.com.autogeneral.testAPI.error.ToDoItemValidationError;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(basePackageClasses= TodoController.class)
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    @ResponseStatus(BAD_REQUEST)
//    @ExceptionHandler(InvalidBracketSequenceException.class)
//    public final ResponseEntity<ToDoItemValidationError.ErrorDetails> invalidBracketSequenceExceptionHandler(InvalidBracketSequenceException exception) {
//        final ToDoItemValidationError.ErrorDetails validationErrorDetails = new ToDoItemValidationError.ErrorDetails("params", "text", exception.getMessage(), "");
//        ToDoItemValidationError exceptionResponse = new ToDoItemValidationError(Arrays.asList(validationErrorDetails));
//        return new ResponseEntity<ToDoItemValidationError.ErrorDetails>(validationErrorDetails, BAD_REQUEST);
//    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ToDoItemNotFoundError> resourceNotFoundExceptionExceptionHandler(ResourceNotFoundException exception) {
        ToDoItemNotFoundError exceptionResponse = new ToDoItemNotFoundError(exception.getMessage());
        return new ResponseEntity<ToDoItemNotFoundError>(exceptionResponse, NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        List<ToDoItemValidationError.ErrorDetails> errorDetails = new ArrayList<>();
        for (FieldError ex : exception.getBindingResult().getFieldErrors() ) {
            errorDetails.add(new ToDoItemValidationError.ErrorDetails("params", ex.getField(), ex.getDefaultMessage(), ""));
        }
        ToDoItemValidationError exceptionResponse = new ToDoItemValidationError(errorDetails);
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }


}