import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SyntaxChecker {
    public static boolean validateCommandToken(String commandToken) {
        for (CommandList c: CommandList.values()) {
            if (c.name().toLowerCase().equals(commandToken)) {
                return true;
            }
        }
        return false;
    }
    public static boolean validateTokens(String commandName, String[] argumentTokens) {
        if (argumentTokens.length == 0) {
            return true;
        }
        String[] thisRegexSequence = CommandList.getRegexSequence(commandName);
        for (int i = 0; i < CommandList.getArgumentCount(commandName); i++) {
            Pattern pattern = Pattern.compile(thisRegexSequence[i]);
            Matcher matcher = pattern.matcher(argumentTokens[i]);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }
    public static boolean isDelimitedWithSpaces(String commandName) {
        return CommandList.valueOf(commandName).ordinal() < 5;
    }
    public static boolean hasArgument(String userInput) {
        return userInput.split(" ", 2).length - 1 == 1;
    }
}
