package command;

import exception.AdamException;
import ui.Parser;

/**
 * The CommandGenerator class generates a command based on the user input.
 */
public class CommandGenerator {

    /**
     * Generates a command based on the user input.
     *
     * @param input The user input.
     * @return The command to be executed.
     * @throws AdamException If an error occurs during the generation of the command.
     */
    public static Command generate(String input) throws AdamException {
        // match input to corresponding enum (also handles invalid input)
        Parser token = Parser.analyzeInput(input);

        switch (token) {
        case EXIT:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case TOGGLE:
            return new ToggleStatusCommand(Parser.splitInput(token, input));

        case HELP:
            return new HelpCommand();

        case DELETE:
            return new DeleteCommand(Parser.splitInput(token, input));

        case FIND:
            return new FindCommand(Parser.splitInput(token, input));

        default:
            return new AddTaskCommand(token, Parser.splitInput(token, input));
        }
    }
}
