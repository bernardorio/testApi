package au.com.autogeneral.testAPI.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;

//public class InvalidBracketSequenceException extends MethodArgumentNotValidException {
public class InvalidBracketSequenceException extends Exception {

    public InvalidBracketSequenceException(final String reason) {
//        super();
        super(reason);
    }
}
