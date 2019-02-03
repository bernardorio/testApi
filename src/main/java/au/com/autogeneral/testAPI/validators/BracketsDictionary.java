package au.com.autogeneral.testAPI.validators;

import java.util.HashMap;
import java.util.Map;

public class BracketsDictionary  {

    private static final Map<Character, Character> BRACKETS_DEFINITION =
        new HashMap<Character, Character>(){{
            put('{', '}');
            put('(', ')');
            put('[', ']');
        }};

    public static boolean isClosingBracket(Character bracket){
        return BRACKETS_DEFINITION.containsValue(bracket);
    }

    public static boolean isOpeningBracket(Character bracket){
        return BRACKETS_DEFINITION.containsKey(bracket);
    }

    public static Character getClosingBracketFor(Character openBracket){
        return BRACKETS_DEFINITION.get(openBracket);
    }

}
