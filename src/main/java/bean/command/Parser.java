package bean.command;

import bean.command.exception.NoValueException;

/**
 * Represents a line of user input. Each Parser object contains a commandArgumentPair
 * and an array of fieldValuePairs which can be easily accessed with various methods.
 */
public class Parser {
    private final String[] fieldValuePairs;
    private final String commandArgumentPair;

    /**
     * Returns the value of a specified field in a given line of user input.
     * (The substring after /{field} and before any next slash)
     *
     * @param field Required field to get the value from
     * @return value of required field
     * @throws NoValueException If no value is found.
     */
    public String getValue(String field) throws NoValueException {
        for (String item : fieldValuePairs) {
            if (item.startsWith(field) && item.contains(" ")) {
                int indexOfFirstSpace = item.indexOf(" ");
                return item.substring(indexOfFirstSpace + 1).trim();
            }
        }
        throw new NoValueException();
    }

    /**
     * Returns the argument in a given line of user input.
     * (The substring after the first word and before any slashes)
     *
     * @return Lateral location.
     * @throws NoValueException If no argument is found.
     */
    public String getArgument() throws NoValueException {
        int indexOfFirstSpace = commandArgumentPair.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            throw new NoValueException();
        } else {
            return commandArgumentPair.substring(indexOfFirstSpace + 1).trim();
        }
    }

    /**
     * Returns the command in a given line of user input.
     * (The first word in the line)
     *
     * @return The user command
     */
    public String getCommand() {
        int indexOfFirstSpace = commandArgumentPair.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            return commandArgumentPair;
        } else {
            return commandArgumentPair.substring(0, indexOfFirstSpace).trim();
        }
    }

    /**
     * Constructs a Parser object.
     * Turns a line of user input into a command-argument pair and an array of
     * field-value pairs as members within the Parser object.
     *
     * @param line User's line of input
     */
    public Parser(String line) {
        int indexOfFirstSlash = line.indexOf('/');
        if (indexOfFirstSlash == -1) {
            commandArgumentPair = line.trim();
            fieldValuePairs = new String[0];
        } else {
            commandArgumentPair = line.substring(0, indexOfFirstSlash);
            fieldValuePairs = line.substring(indexOfFirstSlash + 1).split("/");
            for (String item : fieldValuePairs) {
                item = item.trim();
            }
        }
    }
}
