package command;

import java.util.Optional;

import exception.AdamException;
import ui.Parser;

public class CommandGenerator{
    public static Optional<Command> generate(String input) throws AdamException {
        // match input to corresponding enum (also handles invalid input)
        Parser token = Parser.analyzeInput(input);

        switch (token) {
        case BYE:
            return Optional.of(new ExitCommand());

        case LIST:
            return Optional.of(new ListCommand());

        case TOGGLE:
            return Optional.of(new ToggleStatusCommand(Parser.splitInput(input)));

        default:
            return Optional.of(new AddTaskCommand(token, Parser.splitInput(input)));
        }
    }
}
