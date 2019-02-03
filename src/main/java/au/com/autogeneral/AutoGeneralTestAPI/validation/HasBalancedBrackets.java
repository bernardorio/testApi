package au.com.autogeneral.AutoGeneralTestAPI.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = BracketsSequenceValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface HasBalancedBrackets {
    String message() default "Unbalanced brackets";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

