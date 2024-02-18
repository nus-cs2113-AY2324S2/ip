package ui;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.AdamException;

public enum Parser {
    EXIT("bye|exit|ex"),
    LIST("list"),
    TOGGLE("(mark|unmark)\\s*(\\d+)\\s*"), // accept any number of space using \s*
    TODO("todo\\s*(.+)"), //
    DEADLINE("deadline\\s*(.+)/by\\s*(.+)"), //
    EVENT("event\\s*(.+)/from\\s*(.+)/to\\s*(.+)"), //
    HELP("help|h"),
    DELETE("delete\\s*(\\d+)\\s*");

    private final String commandRegex;

    Parser(String commandRegex) {
        this.commandRegex = commandRegex;
    }

    private String getCommandRegex() {
        return this.commandRegex;
    }

    public static Parser analyzeInput(String input) throws AdamException {
        return Arrays.stream(Parser.values())
                .filter(token -> input.matches(token.getCommandRegex()))
                .findFirst()
                .orElseThrow(() -> new AdamException(Message.INVALID_INPUT_MESSAGE));
    }

    public static String[] splitInput(String input) {
        String[] parsedInput = {};

        Pattern eventPattern = Pattern.compile(EVENT.getCommandRegex());
        Pattern deadlinePattern = Pattern.compile(DEADLINE.getCommandRegex());
        Pattern todoPattern = Pattern.compile(TODO.getCommandRegex());
        Pattern togglePattern = Pattern.compile(TOGGLE.getCommandRegex());
        Pattern deletePattern = Pattern.compile(DELETE.getCommandRegex());

        Matcher eventMatcher = eventPattern.matcher(input);
        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        Matcher todoMatcher = todoPattern.matcher(input);
        Matcher toggleMatcher = togglePattern.matcher(input);
        Matcher deleteMatcher = deletePattern.matcher(input);

        if (eventMatcher.find()) {
            parsedInput = new String[3];
            parsedInput[0] = eventMatcher.group(1).trim();
            parsedInput[1] = eventMatcher.group(2).trim();
            parsedInput[2] = eventMatcher.group(3).trim();
        } else if (deadlineMatcher.find()) {
            parsedInput = new String[2];
            parsedInput[0] = deadlineMatcher.group(1).trim();
            parsedInput[1] = deadlineMatcher.group(2).trim();
        } else if (todoMatcher.find()) {
            parsedInput = new String[1];
            parsedInput[0] = todoMatcher.group(1).trim();
        } else if(toggleMatcher.find()) {
            parsedInput = new String[2];
            parsedInput[0] = toggleMatcher.group(1).trim();
            parsedInput[1] = toggleMatcher.group(2).trim();
        } else if (deleteMatcher.find()) {
            parsedInput = new String[1];
            parsedInput[0] = deleteMatcher.group(1).trim();
        }

        return parsedInput;
    }
}
