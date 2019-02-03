package au.com.autogeneral.testAPI.validators;

import au.com.autogeneral.testAPI.controllers.TasksController;
import au.com.autogeneral.testAPI.exception.InvalidBracketSequenceException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static au.com.autogeneral.testAPI.validators.BracketsValidator.validateBracketsBalance;

public class BracketsSequenceValidator implements ConstraintValidator<HasBalancedBrackets, String>   {
//public class BracketsSequenceValidator implements Validator {

//    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//        HibernateConstraintValidatorContext context = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
//
//        context.buildConstraintViolationWithTemplate( "{foo}" )
//                .addConstraintViolation();
//        constraintValidatorContext.disableDefaultConstraintViolation();
//        constraintValidatorContext
//                .buildConstraintViolationWithTemplate("User already exists!")
//                .addConstraintViolation();
//
//        return false;
//    }


    @Override
    public boolean isValid(String bracketsSequence, ConstraintValidatorContext ctx){
        try {
            validateBracketsBalance(bracketsSequence);
        } catch (InvalidBracketSequenceException e) {

            throw new MethodArgumentNotValidException(
                    new MethodParameter(null, 0),
                    null);
        }
        return true;
    }

}