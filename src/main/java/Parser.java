import java.util.Scanner;
import java.util.regex.MatchResult;

public class Parser {
    private final Scanner inputReader;

    public Parser() {
        this.inputReader = new Scanner(System.in);
    }

    /**
     * Parse user command (e.g., list, deadline, bye)
     */
    public Command parseCommand() {
        return Command.valueOf(inputReader.next().toUpperCase());
    }

    /**
     * Parse arguments provided (e.g., /from, /by, /to)
     */
    public String[] parseArguments(Command userCommand) {
        int expectedArgumentCount;

        switch (userCommand) {
        case DEADLINE:
            inputReader.findInLine("(.+) /by (.+)"); // Set regex format
            expectedArgumentCount = 2;
            break;
        case EVENT:
            inputReader.findInLine("(.+) /from (.+) /to (.+)"); // Set regex format
            expectedArgumentCount = 3;
            break;
        default:
            inputReader.findInLine("(.+)"); // Set regex format
            expectedArgumentCount = 1;
            break;
        }

        MatchResult argumentMatches = inputReader.match();
        String[] arguments = new String[expectedArgumentCount]; // Return arguments in String array

        for (int i = 1; i <= expectedArgumentCount; i++) {
            // Extract provided arguments
            arguments[i - 1] = argumentMatches.group(i).strip();
        }

        return arguments;
    }

    public boolean isDone(Command userCommand) {
        return userCommand.equals(Command.BYE);
    }

    public void clearInput() {
        // Force Scanner to jump to next line
        inputReader.nextLine();
    }

    public boolean hasMoreInput() {
        return inputReader.hasNext();
    }
}
