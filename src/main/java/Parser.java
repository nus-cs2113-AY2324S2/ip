import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String[] parseInput(String input) {
        String[] parsedInput = {};

        String eventRegex = "event\\s(.+)/from\\s(.+)/to\\s(.+)";
        String deadlineRegex = "deadline\\s(.+)/by\\s(.+)";
        String todoRegex = "todo\\s(.+)";

        Pattern eventPattern = Pattern.compile(eventRegex);
        Pattern deadlinePattern = Pattern.compile(deadlineRegex);
        Pattern todoPattern = Pattern.compile(todoRegex);

        Matcher eventMatcher = eventPattern.matcher(input);
        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        Matcher todoMatcher = todoPattern.matcher(input);

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
