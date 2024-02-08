import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Parser {
    BYE("bye"),
    LIST("list"),
    TOGGLE("^(mark [0-9]*|unmark [0-9]*)"),
    TODO("todo\\s(.+)"),
    DEADLINE("deadline\\s(.+)/by\\s(.+)"),
    EVENT("event\\s(.+)/from\\s(.+)/to\\s(.+)");

    private final String commandRegex;

    private Parser(String commandRegex) {
        this.commandRegex = commandRegex;
    }

    private String getCommandRegex() {
        return this.commandRegex;
    }

    public static Parser analyzeInput(String input) {
        return Arrays.stream(Parser.values())
                .filter(token -> input.matches(token.getCommandRegex()))
                .findFirst()
                .orElseThrow();
    }

    public static String[] splitInput(String input) {
        String[] parsedInput = {};
        Matcher eventMatcher = Pattern.compile(EVENT.getCommandRegex()).matcher(input);
        Matcher deadlineMatcher = Pattern.compile(DEADLINE.getCommandRegex()).matcher(input);
        Matcher todoMatcher = Pattern.compile(TODO.getCommandRegex()).matcher(input);

        if (eventMatcher.find()) {
            parsedInput = new String[3];
            parsedInput[0] = eventMatcher.group(1);
            parsedInput[1] = eventMatcher.group(2);
            parsedInput[2] = eventMatcher.group(3);
        } else if (deadlineMatcher.find()) {
            parsedInput = new String[2];
            parsedInput[0] = deadlineMatcher.group(1);
            parsedInput[1] = deadlineMatcher.group(2);
        } else if (todoMatcher.find()) {
            parsedInput = new String[1];
            parsedInput[0] = todoMatcher.group(1);
        }

        return parsedInput;
    }
}
