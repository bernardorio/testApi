package au.com.autogeneral.AutoGeneralTestAPI.exception;

public class InvalidBracketSequenceException extends Exception {

    public InvalidBracketSequenceException(final String reason) {
        super(reason);
    }
}
