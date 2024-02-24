import java.util.ArrayList;
import java.util.Scanner;

public class CommandExecutor {
    static boolean isRunning = true;
    static ArrayList<Task> tasks = new ArrayList<Task>();
    static Scanner in = new Scanner(System.in);
    static String userInput;
    static CommandParser userCommandReader;
    public static void beginListening() {
        userInput = in.nextLine();
    }
    public static void processInput() throws ProcessInputException{
        try {
            userCommandReader = new CommandParser(userInput);
        } catch (IllegalCommandException e) {
            Formatter.printErrorWrongCommand();
            throw new ProcessInputException();
        } catch (ArgumentMismatchException e1) {
            SyntaxChecker.isArgumentMatch(e1.commandName,e1.argumentCount);
            throw new ProcessInputException();
        } catch (BadTokenException e2) {
            Formatter.printErrorBadTokens();
            throw new ProcessInputException();
        }
    }

    public static void executeCommand() {
            CommandList selectedCommand = CommandList.valueOf(userCommandReader.getCommandName());
            switch (selectedCommand) {
            case BYE:
                CommandList.executeBye();
                break;
            case LIST:
                CommandList.executeList();
                break;
            case MARK:
                CommandList.executeMark(userCommandReader);
                break;
            case TODO:
                CommandList.executeTodo(userCommandReader);
                break;
            case DELETE:
                CommandList.executeDelete(userCommandReader);
                break;
            case EVENT:
                CommandList.executeEvent(userCommandReader);
                break;
            case UNMARK:
                CommandList.executeUnmark(userCommandReader);
                break;
            case DEADLINE:
                CommandList.executeDeadline(userCommandReader);
                break;
            default:
                Formatter.printErrorUnknown();
        }
    }
}
