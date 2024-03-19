/**
 * Parses the user input by splitting it, extracting the type,
 * and making it into a Command object.
 */
public class Parser {

    /**
     * Parses the full command string into a Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return The Command object representing the parsed command, containing the command type and arguments.
     * @throws MavisException If an error occurs during parsing.
     */
    public static Command parse(String fullCommand) throws MavisException {
        String[] splitInput = fullCommand.split(" ", 2);
        String command = splitInput[0];
        CommandType type = CommandType.fromString(command);
        if (type == null) {
            throw new MavisException();
        }
        String[] args = splitInput.length > 1 ? splitInput[1].split("\\s+(?=/)", -1) : new String[0];
        return new Command(type, args);
    }
}
