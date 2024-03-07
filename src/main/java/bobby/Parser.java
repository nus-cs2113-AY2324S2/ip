package bobby;

/**
 * Parses user input and extracts relevant information.
 */
public class Parser {

    /**
     * Parses the command from the user input.
     *
     * @param input The user input.
     * @return The command extracted from the input.
     */
    public String parseCommand(String input) {
        String command;
        if (input.indexOf(' ') > 0) {
            command = input.substring(0, input.indexOf(' '));
        } else {
            command = input;
        }
        return command;
    }

    /**
     * Parses the description from the todo input.
     *
     * @param input The todo input.
     * @return The description extracted from the input.
     * @throws BobbyException If the input is invalid.
     */
    public String parseTodoDescription(String input) throws BobbyException {
        if (input.length() < 5 || input.substring(5).trim().isEmpty()) {
            throw new BobbyException();
        }
        return input.substring(5);
    }

    /**
     * Parses the description from the deadline input.
     *
     * @param input The deadline input.
     * @return The description extracted from the input.
     */
    public String parseDeadlineDescription(String input) {
        return input.substring(9, input.indexOf("/by") - 1);
    }

    /**
     * Parses the deadline from the deadline input.
     *
     * @param input The deadline input.
     * @return The deadline extracted from the input.
     */
    public String parseDeadlineBy(String input) {
        return input.substring(input.indexOf("/by") + 4);
    }

    /**
     * Parses the description from the event input.
     *
     * @param input The event input.
     * @return The description extracted from the input.
     */
    public String parseEventDescription(String input) {
        return input.substring(6, input.indexOf("/from") - 1);
    }

    /**
     * Parses the deadline from the event input.
     *
     * @param input The event input.
     * @return The deadline extracted from the input.
     */
    public String parseEventBy(String input) {
        return input.substring(input.indexOf("/to") + 4);
    }

    /**
     * Parses the start time from the event input.
     *
     * @param input The event input.
     * @return The start time extracted from the input.
     */
    public String parseEventFrom(String input) {
        return input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
    }

    public String parseKeyword(String input) {
        return input.substring(5);
    }
}
