package au.com.autogeneral.AutoGeneralTestAPI.validation;

import au.com.autogeneral.AutoGeneralTestAPI.exception.InvalidBracketSequenceException;

import java.util.Stack;

import static au.com.autogeneral.AutoGeneralTestAPI.validation.BracketsDictionary.getClosingBracketFor;
import static au.com.autogeneral.AutoGeneralTestAPI.validation.BracketsDictionary.isClosingBracket;
import static au.com.autogeneral.AutoGeneralTestAPI.validation.BracketsDictionary.isOpeningBracket;
import static java.lang.String.format;

public class BracketsValidator {

    public static boolean validateBracketsBalance(String bracketsInput) throws InvalidBracketSequenceException {
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

    private static boolean handleClosingBracket(Stack<Character> characterStack, Character inputCharacter) throws InvalidBracketSequenceException {
        if(characterStack.empty()){
            throw new InvalidBracketSequenceException(format("Closing bracket without any opening bracket: '%s'", inputCharacter));
        } else if(!getClosingBracketFor(characterStack.pop()).equals(inputCharacter)){
            throw new InvalidBracketSequenceException(format("Closing bracket on non-matching opening bracket: '%s'", inputCharacter));
        }
        return true;
    }

    private static void handleOpeningBracket(Stack<Character> charaterStack, Character bracket) {
        charaterStack.push(bracket);
    }

}
