import java.util.Scanner;

public class CommandExecutor {
    static boolean isRunning = true;
    static final int MAX_NUMBERED_LIST_LENGTH = 100;
    static Task[] tasks = new Task[MAX_NUMBERED_LIST_LENGTH];
    static int listCount = 0;
    static Scanner in = new Scanner(System.in);
    static String userInput;

    public static void beginListening() {
        userInput = in.nextLine();
    }

    public static void executeCommand(CommandParser readUserCommand) {
        if (!readUserCommand.getIsGoodTokens()) {
            Formatter.printErrorExecutionFail();
        } else {
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
}
