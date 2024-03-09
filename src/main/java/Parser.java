import java.text.ParseException;

/**
 * Parses user commands and returns corresponding command objects.
 */
public class Parser {

    /**
     * Parses the user command and returns the corresponding command object.
     *
     * @param command The user command to parse.
     * @return A Command object representing the parsed command.
     * @throws ParseException If the command cannot be parsed.
     */
    public static Command parse(String command) throws ParseException {
        if (command.startsWith("todo")) {
            // Parse additional information if needed and create a TodoCommand
            return new TodoCommand();
        } else {
            throw new ParseException("Invalid command");
        }
    }


}
