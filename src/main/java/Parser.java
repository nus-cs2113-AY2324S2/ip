/**
 * This class is responsible for parsing user commands into executable commands.
 * It interprets user input and maps it to specific Command objects based on the input.
 */
public class Parser {

    /**
     * Parses a full command string into a Command object.
     * @param fullCommand the full command string entered by the user
     * @return a Command object representing the user's command
     * @throws DukeException if the command is not recognized or if there's an error in parsing
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ");
        String command = parts[0];

        if ("list".equalsIgnoreCase(command)) {
            return new ListCommand();
        } else if ("mark".equalsIgnoreCase(command)) {
            return new MarkCommand(Integer.parseInt(parts[1]));
        } else if ("unmark".equalsIgnoreCase(command)) {
            return new UnmarkCommand(Integer.parseInt(parts[1]));
        } else if ("todo".equalsIgnoreCase(command)) {
            return new AddTodoCommand(fullCommand.substring(4).trim());
        } else if ("deadline".equalsIgnoreCase(command)) {
            return new AddDeadlineCommand(fullCommand.substring(8).trim());
        } else if ("event".equalsIgnoreCase(command)) {
            return new AddEventCommand(fullCommand.substring(5).trim());
        } else if ("delete".equalsIgnoreCase(command)) {
            return new DeleteCommand(Integer.parseInt(parts[1]));
        } else if ("bye".equalsIgnoreCase(command)) {
            return new ExitCommand();
        } else if ("find".equalsIgnoreCase(command)) {
            // Assuming FindCommand constructor takes a keyword as a parameter
            return new FindCommand(fullCommand.substring(4).trim());
        } else {
            return new UnknownCommand(fullCommand);
        }
    }
}

