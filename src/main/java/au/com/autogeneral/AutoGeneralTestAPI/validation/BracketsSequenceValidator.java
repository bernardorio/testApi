package au.com.autogeneral.AutoGeneralTestAPI.validation;

import au.com.autogeneral.AutoGeneralTestAPI.exception.InvalidBracketSequenceException;
import org.slf4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static au.com.autogeneral.AutoGeneralTestAPI.validation.BracketsValidator.validateBracketsBalance;
import static org.slf4j.LoggerFactory.getLogger;

public class BracketsSequenceValidator implements ConstraintValidator<HasBalancedBrackets, String> {

    private static final Logger logger = getLogger(BracketsSequenceValidator.class);

    @Override
    public boolean isValid(String bracketsSequence, ConstraintValidatorContext ctx){
        try {
            validateBracketsBalance(bracketsSequence);
        } catch (InvalidBracketSequenceException e) {
            logger.info("Invalid brackets sequence provided: '{}'", bracketsSequence, e);
            return false;
        }

        return true;
    }

}