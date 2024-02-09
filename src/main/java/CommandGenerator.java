import java.util.Optional;
import java.util.Scanner;

public class CommandGenerator{
    private static final Scanner scanner = new Scanner(System.in);
    public static Optional<Command> generate() {
        String input = scanner.nextLine();
        // match input to corresponding enum (also handles invalid input)
        Parser token = Parser.analyzeInput(input);

        switch (token) {
        case BYE:
            scanner.close();
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
