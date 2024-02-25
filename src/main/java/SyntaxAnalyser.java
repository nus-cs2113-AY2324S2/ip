import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxAnalyser {
    private static String VALID_TASK_INDEX_REGEX = "^[0-9]{1,3}$";
    private static final String UNRESTRICTED_CHAR_LENGTH_REGEX = ".+";
    private static final String[][] lutRegexSeq = {
            {},
            {},
            {VALID_TASK_INDEX_REGEX},
            {VALID_TASK_INDEX_REGEX},
            {UNRESTRICTED_CHAR_LENGTH_REGEX},
            {UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX},
            {UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX},
            {VALID_TASK_INDEX_REGEX},
            //insert new command syntax here
    };

    public static int getArgumentCount(String COMMAND_NAME) {
        return getRegexSeq(COMMAND_NAME).length;
    }
    public static String[] getRegexSeq(String COMMAND_NAME) {
        return lutRegexSeq[CommandList.valueOf(COMMAND_NAME).ordinal()];
    }
    public static boolean validateUserCommandName(String USER_COMMAND_NAME) {
        for (CommandList c : CommandList.values()) {
            if (c.name().toLowerCase().equals(USER_COMMAND_NAME)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateTokens(String COMMAND_NAME, String[] argumentTokens) {
        String[] cmdNameRegexSeq = getRegexSeq(COMMAND_NAME);
        for (int i = 0; i < getArgumentCount(COMMAND_NAME); i++) {
            Pattern pattern = Pattern.compile(cmdNameRegexSeq[i]);
            Matcher matcher = pattern.matcher(argumentTokens[i]);
            if (!matcher.find()) {
                Formatter.printErrorWrongArgumentType(COMMAND_NAME,cmdNameRegexSeq[i],i);
                return false;
            }
        }
        return true;
    }
}
