package Backend;

/**
 * This class validates whether a command is typed in the correct format
 */
public class StringValidator {
    /**
     * Validates correctly formatted deadline command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "deadline <description> /by <time>" format
     */
    public static void validateDeadlineFormat(String input) throws InvalidFormatException {
        // Regular expression to match "deadline <description> /by <time>" format
        String regex = "(?i)deadline (.*?) /by\\s+(.*?)";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid deadline format. Expected format: deadline <description> /by <time>.");
        }
    }
    /**
     * Validates correctly formatted todo command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "todo <description>" format
     */
    public static void validateTodoFormat(String input) throws InvalidFormatException {
        // Regular expression to match "todo <description>"
        String regex = "(?i)todo (.*?)";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid todo format. Expected format: todo <description>");
        }
    }
    /**
     * Validates correctly formatted event command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "event <description> /from <time> /to <time>" format
     */
    public static void validateEventFormat(String input) throws InvalidFormatException {
        // Regular expression to match "event <description> /from <time> /to <time>"
        String regex = "(?i)event (.*?) /from\\s+(.*?)\\s/to\\s+(.*?)";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid event format. Expected format: event <description> /from <time> /to <time>");
        }
    }
    /**
     * Validates correctly formatted mark command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "mark #" format
     */
    public static void validateMarkFormat(String input) throws InvalidFormatException {
        // Regular expression to match "mark #"
        String regex = "(?i)mark \\d+";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid mark command. Expected format: mark <task number>");
        }
    }
    /**
     * Validates correctly formatted unmark command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "unmark #" format
     */
    public static void validateUnmarkFormat(String input) throws InvalidFormatException {
        // Regular expression to match "unmark #"
        String regex = "(?i)unmark \\d+";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid unmark command. Expected format: unmark <task number>");
        }
    }
    /**
     * Validates correctly formatted delete command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "delete #" format
     */
    public static void validateDeleteFormat(String input) throws InvalidFormatException {
        // Regular expression to match "delete #"
        String regex = "(?i)delete \\d+";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid delete command. Expected format: delete <task number>");
        }
    }
}
