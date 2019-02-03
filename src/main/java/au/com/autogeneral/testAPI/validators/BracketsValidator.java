package au.com.autogeneral.testAPI.validators;

import au.com.autogeneral.testAPI.exception.InvalidBracketSequenceException;

import java.util.Stack;

import static au.com.autogeneral.testAPI.validators.BracketsDictionary.getClosingBracketFor;
import static au.com.autogeneral.testAPI.validators.BracketsDictionary.isClosingBracket;
import static au.com.autogeneral.testAPI.validators.BracketsDictionary.isOpeningBracket;
import static java.lang.String.format;

public class BracketsValidator {

    public static boolean validateBracketsBalance(final String bracketsInput) throws InvalidBracketSequenceException {
        Stack<Character> characterStack = new Stack<>();

        for (Character inputCharacter : bracketsInput.toCharArray()) {
            if (isOpeningBracket(inputCharacter)) {
                handleOpeningBracket(characterStack, inputCharacter);
            } else if (isClosingBracket(inputCharacter)) {
                handleClosingBracket(characterStack, inputCharacter);
            } else {
                throw new InvalidBracketSequenceException(format("Invalid Character: '%s'", inputCharacter));
            }
        }

        if (!characterStack.empty()) {
            throw new InvalidBracketSequenceException("Opening brackets have no corresponding closing brackets");
        }

        return true;
    }

    private static boolean handleClosingBracket(final Stack<Character> characterStack, final Character inputCharacter) throws InvalidBracketSequenceException {
        if(characterStack.empty()){
            throw new InvalidBracketSequenceException(format("Closing bracket without any opening bracket: '%s'", inputCharacter));
        } else if(!getClosingBracketFor(characterStack.pop()).equals(inputCharacter)){
            throw new InvalidBracketSequenceException(format("Closing bracket on non-matching opening bracket: '%s'", inputCharacter));
        }
        return true;
    }

    private static void handleOpeningBracket(final Stack<Character> charaterStack, final Character bracket) {
        charaterStack.push(bracket);
    }

}
