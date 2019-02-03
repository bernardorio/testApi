package au.com.autogeneral.testAPI.validators;

import au.com.autogeneral.testAPI.exception.InvalidBracketSequenceException;
import org.junit.Test;

import java.util.List;

import static au.com.autogeneral.testAPI.validators.BracketsValidator.validateBracketsBalance;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class BracketsValidatorTest {

    public static final List<String> LIST_OF_VALID_BRACKETS_SEQUENCE =  asList("({[]})", "{}", "()", "{}[][]");
    public static final List<String> LIST_OF_INVALID_BRACKETS_SEQUENCE =  asList("{[}]", "[{)]", "]{}[", "{{{}[");
    public static final List<String> LIST_OF_NON_BRACKET_SEQUENCE =  asList("{[ _ }]", "[a]", "{s}");

    @Test
    public void shouldBeValidForBalancedBracketSequence() throws InvalidBracketSequenceException {
        for(String sequence: LIST_OF_VALID_BRACKETS_SEQUENCE) {
            assertThat(validateBracketsBalance(sequence))
                    .withFailMessage("Expected sequence '%s' to be valid", sequence)
                    .isTrue();
        }
    }

    @Test
    public void shouldBeInvalidForNonBracketCharacter() throws InvalidBracketSequenceException {
        for(String sequence: LIST_OF_NON_BRACKET_SEQUENCE) {
            assertThat(validateBracketsBalance(sequence))
                    .withFailMessage("Expected sequence '%s' to be invalid", sequence)
                    .isFalse();
        }
    }

    @Test
    public void shouldBeInValidForUnbalancedBracketSequence() throws InvalidBracketSequenceException {
        for(String sequence: LIST_OF_INVALID_BRACKETS_SEQUENCE) {
            assertThat(validateBracketsBalance(sequence))
                    .withFailMessage("Expected sequence '%s' to be invalid", sequence)
                    .isFalse();
        };
    }

}
