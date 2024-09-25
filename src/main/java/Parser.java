/**
 * Parser class handles the parsing of user commands for the Duke application.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand Full user input command.
     * @return Command object based on the parsed input.
     * @throws DukeException If an error occurs during parsing or if the command is unknown.
     */
    public static Command parse(String fullCommand) throws DukeException {

        // Split the full command into parts
        String[] parts = fullCommand.split(" ");
        String command = parts[0].toLowerCase();

        // Determine the type of command and create the appropriate Command object
        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(parts[1]));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(parts[1]));
        case "todo":
            return new TodoCommand(fullCommand.substring(4).trim());
        case "deadline":
            return new DeadlineCommand(fullCommand.substring(8).trim());
        case "event":
            return new EventCommand(fullCommand.substring(5).trim());
        case "delete":
            return new DeleteCommand(Integer.parseInt(parts[1]));
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(fullCommand.substring(4).trim());
        default:
            throw new DukeException("Unknown command.");
        }
    }
}
