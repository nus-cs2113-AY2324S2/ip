import java.util.Scanner;
import java.util.regex.MatchResult;

public class Parser {
    private final Scanner inputReader;

    public Parser() {
        this.inputReader = new Scanner(System.in);
    }

    public String parseCommand() {
        return inputReader.next();
    }

    public void clearInput() {
        inputReader.nextLine();
    }

    public String[] parseArguments(int expectedArgumentCount) {
        String[] arguments = new String[expectedArgumentCount];
        MatchResult argumentMatches;

        switch (expectedArgumentCount) {
        case 2:
            // Deadline command
            inputReader.findInLine("(.+) /by (.+)"); // Set regex format
            break;
        case 3:
            // Event command
            inputReader.findInLine("(.+) /from (.+) /to (.+)"); // Set regex format
            break;
        default:
            inputReader.findInLine("(.+)"); // Set regex format
            break;
        }

        argumentMatches = inputReader.match();

        for (int i = 1; i <= expectedArgumentCount; i++) {
            arguments[i - 1] = argumentMatches.group(i).strip();
        }

        return arguments;
    }
}
