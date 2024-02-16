import java.util.Scanner;

public class CommandExecutor {
    static boolean isRunning = true;
    static final int MAX_NUMBERED_LIST_LENGTH = 100;
    static Task[] tasks = new Task[MAX_NUMBERED_LIST_LENGTH];
    static int listCount = 0;
    static Scanner in = new Scanner(System.in);
    static String userInput;
    static CommandParser readUserCommand;
    public static void beginListening() {
        userInput = in.nextLine();
    }
    public static void processInput() throws ProcessInputException{
        try {
            readUserCommand = new CommandParser(userInput);
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
            CommandList selectedCommand = CommandList.valueOf(readUserCommand.getCommandName());
            switch (selectedCommand) {
            case BYE:
                CommandList.executeBye();
                break;
            case LIST:
                CommandList.executeList();
                break;
            case MARK:
                CommandList.executeMark(readUserCommand);
                break;
            case TODO:
                CommandList.executeTodo(readUserCommand);
                break;
            case EVENT:
                CommandList.executeEvent(readUserCommand);
                break;
            case UNMARK:
                CommandList.executeUnmark(readUserCommand);
                break;
            case DEADLINE:
                CommandList.executeDeadline(readUserCommand);
                break;
            default:
                Formatter.printErrorUnknown();
        }
    }
}
