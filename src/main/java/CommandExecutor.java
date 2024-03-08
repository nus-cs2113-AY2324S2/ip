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
            int userArgumentCount = e1.userArgumentCount;
            int correctArgumentCount = SyntaxAnalyser.getArgumentCount(e1.userCommandName);
            Formatter.printErrorArgumentsMismatch(e1.userCommandName, userArgumentCount, correctArgumentCount);
            throw new ProcessInputException();
        } catch (BadTokenException e2) {
            Formatter.printErrorBadTokens();
            throw new ProcessInputException();
        }
    }

    public static void executeCommand() {
            CommandList selectedCommand = CommandList.valueOf(userCommandReader.getCommandName());
            int userSelectedIndex;
            switch (selectedCommand) {
            case BYE:
                CommandList.executeBye();
                break;
            case LIST:
                CommandList.executeList();
                break;
            case MARK:
                userSelectedIndex = CommandList.executeMark(userCommandReader);
                CacheManager.updateCache();
                break;
            case TODO:
                Todo newTodo = CommandList.executeTodo(userCommandReader);
                CacheManager.updateCache(newTodo);
                break;
            case EVENT:
                Event newEvent = CommandList.executeEvent(userCommandReader);
                CacheManager.updateCache(newEvent);
                break;
            case UNMARK:
                userSelectedIndex = CommandList.executeUnmark(userCommandReader);
                CacheManager.updateCache();
                break;
            case DEADLINE:
                Deadline newDeadline = CommandList.executeDeadline(userCommandReader);
                CacheManager.updateCache(newDeadline);
                break;
            case DELETE:
                userSelectedIndex = CommandList.executeDelete(userCommandReader);
                CacheManager.updateCache();
                break;
            case FIND:
                CommandList.executeFind(userCommandReader);
                break;
            default:
                Formatter.printErrorUnknown();
        }
    }
}
