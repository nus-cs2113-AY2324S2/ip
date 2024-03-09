/**
 * This class parses user input strings into Command objects to be executed.
 */
public class Parser {
    private final String userInput;

    /**
     * Constructor for the Parser class.
     *
     * @param userInput the user input string to parse
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses the user input string and returns the corresponding Command object,
     * or null if the input doesn't match any recognized commands.
     *
     * @return the Command object representing the parsed command, or null if not recognized
     */
    public Command parseCommand() {
        if (userInput.startsWith("mark")) {
            return new MarkCommand(Integer.parseInt(userInput.substring(5)) - 1);
        } else if (userInput.startsWith("unmark")) {
            return new UnmarkCommand(Integer.parseInt(userInput.substring(7)) - 1);
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("todo")) {
            if (userInput.trim().length() > 4) {
                String description = userInput.substring(5).trim();
                return new AddTaskCommand(new ToDo(description));
            }
        } else if (userInput.startsWith("deadline")) {
            if (userInput.trim().length() > 8) {
                String[] parts = userInput.substring(9).split("/by");
                String description = parts[0].trim();
                String by = parts[1].trim();
                return new AddTaskCommand(new Deadline(description, by));
            }
        } else if (userInput.startsWith("event")) {
            if (userInput.trim().length() > 5) {
                String[] parts = userInput.substring(6).split("/from|/to");
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                return new AddTaskCommand(new Event(description, from, to));
            }
        } else if (userInput.startsWith("delete")) {
            return new RemoveTaskCommand(Integer.parseInt(userInput.split(" ")[1]) - 1);
        } else if (userInput.startsWith("find")) {
            if (userInput.trim().length() > 5) {
                String keyword = userInput.substring(5).trim();
                return new SearchCommand(keyword);
            }
        }
        return null;
    }
}