import java.util.Optional;

public class CommandGenerator{
    public static Optional<Command> generate(String input) {
        switch (Parser.analyzeInput(input)) {
        case BYE:
            return Optional.of(new ExitCommand());

        case LIST:
            return Optional.of(new ListCommand());

        case TOGGLE:
            return Optional.of(new ToggleStatusCommand(input));

        default:
            return Optional.of(new AddTaskCommand(Parser.splitInput(input)));
        }
    }
}
