package Backend;

public class StringValidator {
    public static void validateDeadlineFormat(String input) throws InvalidFormatException {
        // Regular expression to match "deadline <description> /by <time>" format
        String regex = "(?i)deadline (.*?) /by\\s+(.*?)";

        // Check if the input matches the expected format
        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid deadline format. Expected format: deadline <description> /by <time>.");
        }
    }
    public static void validateTodoFormat(String input) throws InvalidFormatException {
        // Regular expression to match "todo <description>"
        String regex = "(?i)todo (.*?)";

        // Check if the input matches the expected format
        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid todo format. Expected format: todo <description>");
        }
    }
    public static void validateEventFormat(String input) throws InvalidFormatException {
        // Regular expression to match "event <description> /from <time> /to <time>"
        String regex = "(?i)event (.*?) /from\\s+(.*?)\\s/to\\s+(.*?)";

        // Check if the input matches the expected format
        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid event format. Expected format: event <description> /from <time> /to <time>");
        }
    }

    public static void validateMarkFormat(String input) throws InvalidFormatException {
        // Regular expression to match "mark #"
        String regex = "(?i)mark \\d+";

        // Check if the input matches the expected format
        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid mark command. Expected format: mark <task number>");
        }
    }

    public static void validateUnmarkFormat(String input) throws InvalidFormatException {
        // Regular expression to match "mark #"
        String regex = "(?i)unmark \\d+";

        // Check if the input matches the expected format
        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid unmark command. Expected format: unmark <task number>");
        }
    }

    public static void validateDeleteFormat(String input) throws InvalidFormatException {
        // Regular expression to match "delete #"
        String regex = "(?i)delete \\d+";

        // Check if the input matches the expected format
        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid delete command. Expected format: delete <task number>");
        }
    }
}
