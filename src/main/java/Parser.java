public class Parser {
    public static Command parse(String fullCommand) throws DukeException {

        String[] parts = fullCommand.split(" ");
        String command = parts[0].toLowerCase();

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