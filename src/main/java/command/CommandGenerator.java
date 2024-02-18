package command;

import exception.AdamException;
import ui.Parser;

public class CommandGenerator{
    public static Command generate(String input) throws AdamException {
        // match input to corresponding enum (also handles invalid input)
        Parser token = Parser.analyzeInput(input);

        switch (token) {
        case EXIT:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case TOGGLE:
            return new ToggleStatusCommand(Parser.splitInput(input));

        case HELP:
            return new HelpCommand();

        case DELETE:
            return new DeleteCommand(Parser.splitInput(input));

        default:
            return new AddTaskCommand(token, Parser.splitInput(input));
        }
    }
}
