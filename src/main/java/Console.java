import javax.swing.undo.UndoManager;
import java.util.Scanner;

public class Console {
    private final static String NAME = "   ('-.                 ('-.  ) (`-.               .-')    \n"
            + "  ( OO ).-.           _(  OO)  ( OO ).            ( OO ).  \n"
            + "  / . --. / ,--.     (,------.(_/.  \\_)-. ,-.-') (_)---\\_) \n"
            + "  | \\-.  \\  |  |.-')  |  .---' \\  \\/  /  |  |OO)/    _ |  \n"
            + ".-'-'  |  | |  | OO ) |  |      \\  ; /    |  |  \\\\  :` `.  \n"
            + " \\| |_.'  | |  |`-' |(|  '--.    \\   \\ |  |  |(_/ '..`''.) \n"
            + "  |  .-.  |(|  '---.' |  .--'   .' ,  \\_),|  |_.'.-._)   \\ \n"
            + "  |  | |  | |      |  |  `---. /  .''  \\(_|  |   \\       / \n"
            + "  `--' `--' `------'  `------''--'   '--' `--'    `-----'  \n";
    protected final static String LINEBREAK = "____________________________________________________________";
    private final static String GREETING = "Hello, I'm Alexis.\n"
            + "What can I do for you?";
    private final static String GOODBYE = "Bye. Hope to see you again soon!";

    private final static String ERROR = "Sorry bro I didn't quite get what you wanna do. Please enter a valid command.";


    public static void printWelcomeMessage() {
        System.out.println(NAME);
        System.out.println(LINEBREAK);
        System.out.println(GREETING);
        System.out.println(LINEBREAK);
    }

    public static void printErrorMessage() {
        System.out.println(LINEBREAK);
        System.out.println(ERROR);
        System.out.println(LINEBREAK);
    }

    public static void processUserInput(TaskList tasks, Scanner in) {
        while (true) {
            String line = in.nextLine();
            Command command = Command.getFirstWord(line);
            String description = Command.getDescription(line);

            if (command == null) {
                continue;
            }
            switch (command) {
            case BYE:
                return;
            case LIST:
                printTasksToConsole(tasks);
                break;
            case MARK:
                printMarkedItemToConsole(tasks, line);
                break;
            case UNMARK:
                printUnmarkedItemToConsole(tasks, line);
                break;
            case TODO:
                printTaskToConsole(tasks, TaskType.TODO, description);
                break;
            case DEADLINE:
                printTaskToConsole(tasks, TaskType.DEADLINE, description);
                break;
            case EVENT:
                printTaskToConsole(tasks, TaskType.EVENT, description);
                break;
            }
        }
    }

    private static void printTasksToConsole(TaskList tasks) {
        System.out.println(Console.LINEBREAK);
        tasks.printTasks();
        System.out.println(Console.LINEBREAK);
    }

    private static void printMarkedItemToConsole(TaskList tasks, String line) {
        System.out.println(Console.LINEBREAK);
        tasks.markTask(line);
        System.out.println(Console.LINEBREAK);
    }

    private static void printUnmarkedItemToConsole(TaskList tasks, String line) {
        System.out.println(Console.LINEBREAK);
        tasks.unmarkTask(line);
        System.out.println(Console.LINEBREAK);
    }

    private static void printTaskToConsole(TaskList tasks, TaskType todo, String description) {
        System.out.println(Console.LINEBREAK);
        tasks.addTask(todo, description);
        System.out.println(Console.LINEBREAK);
    }

    public static void printGoodbyeMessage() {
        System.out.println(LINEBREAK);
        System.out.println(GOODBYE);
        System.out.println(LINEBREAK);
    }
}
