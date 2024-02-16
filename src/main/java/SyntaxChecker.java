import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxChecker {
    public static boolean validateCommandToken(String commandToken) {
        for (CommandList c : CommandList.values()) {
            if (c.name().toLowerCase().equals(commandToken)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateTokens(String commandName, String[] argumentTokens, int argumentCount) {
        if (argumentTokens.length == 0) {
            return true;
        }
        if (!isArgumentMatch(commandName,argumentCount)) {
            return false;
        }
        String[] thisRegexSequence = CommandList.getRegexSequence(commandName);
        for (int i = 0; i < CommandList.getArgumentCount(commandName); i++) {
            Pattern pattern = Pattern.compile(thisRegexSequence[i]);
            Matcher matcher = pattern.matcher(argumentTokens[i]);
            if (!matcher.find()) {
                Formatter.printErrorWrongArgumentType(commandName,thisRegexSequence[i],i);
                return false;
            }
        }
        return true;
    }

    public static boolean hasArgument(String userInput) {
        return userInput.split(" ", 2).length - 1 == 1;
    }
    public static boolean isArgumentMatch(String commandName, int argumentCount) {
        int correctArgumentCount = CommandList.getArgumentCount(commandName);
        if (argumentCount == correctArgumentCount) {
            return true;
        }
        Formatter.printErrorArgumentsMismatch(argumentCount,correctArgumentCount);
        return false;
    }
}
