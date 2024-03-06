package ui;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import exception.AdamException;

/**
 * The Parser class is responsible for analyzing and splitting user input.
 */
public enum Parser {
    // token list
    // accept any number of space using \s*
    EXIT("bye|ex|q"),
    LIST("list|ls"),
    HELP("help|h"),
    TOGGLE("(mark|unmark)\\s*(\\d+)\\s*"),
    TODO("todo\\s*(.+)"),
    DEADLINE("deadline\\s*(.+)/by\\s*(.+)"),
    EVENT("event\\s*(.+)/from\\s*(.+)/to\\s*(.+)"),
    DELETE("delete\\s*(\\d+)\\s*"),
    FIND("find\\s*(.+)");

    private final String commandRegex;

    Parser(String commandRegex) {
        this.commandRegex = commandRegex;
    }

    private String getCommandRegex() {
        return this.commandRegex;
    }

    /**
     * Analyzes the given input and returns the corresponding token.
     *
     * @param input The input to be analyzed.
     * @return The corresponding token.
     * @throws AdamException If the input does not match any token.
     */
    public static Parser analyzeInput(String input) throws AdamException {
        return Arrays.stream(Parser.values())
                .filter(token -> input.matches(token.getCommandRegex()))
                .findFirst()
                .orElseThrow(() -> new AdamException(Message.INVALID_INPUT_MESSAGE));
    }

    /**
     * Splits the given input based on the given token.
     *
     * @param token The token to be used for splitting the input.
     * @param input The input to be split.
     * @return The split input.
     */
    public static String[] splitInput(Parser token, String input) {
        Pattern matchedPattern = Pattern.compile(token.getCommandRegex());
        Matcher matcher = matchedPattern.matcher(input);
        matcher.find();

        return IntStream.rangeClosed(1, matcher.groupCount())
                .mapToObj(i -> matcher.group(i).trim())
                .toArray(String[]::new);
    }
}
