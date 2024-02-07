import java.util.Optional;
import java.util.function.Function;

public class CommandGenerator implements Function<String, Optional<Command>> {
    @Override
    public Optional<Command> apply(String input) {
        if (input.equals("bye")) {
            return Optional.of(new ExitCommand());
        } else if (input.equals("list")) {
            return Optional.of(new ListCommand());
        } else if (input.matches("^(mark [0-9]*|unmark [0-9]*)")) {
            return Optional.of(new ToggleStatusCommand(input));
        }
        return Optional.of(new AddTaskCommand(Parser.parseInput(input)));
    }
}
