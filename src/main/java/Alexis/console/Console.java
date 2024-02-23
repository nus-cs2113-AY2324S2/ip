package Alexis.console;

import Alexis.exception.MissingFieldException;
import Alexis.task.TaskList;
import Alexis.task.TaskType;

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
    protected final static String LINE_BREAK = "____________________________________________________________";
    private final static String GREETING = "Hello, I'm Alexis.\n"
            + "What can I do for you?";
    private final static String GOODBYE = "Bye. Hope to see you again soon!";

    private final static String COMMAND_ERROR = "Sorry bro I don't quite get what you wanna do. Please enter a valid command.";

    private final static String DESCRIPTION_ERROR = "Sorry bro I don't know what task you wanna add. Please add a description.";

    public static void printWelcomeMessage() {
        System.out.println(NAME);
        System.out.println(LINE_BREAK);
        System.out.println(GREETING);
        System.out.println(LINE_BREAK);
    }

    public static void printCommandErrorMessage() {
        System.out.println(LINE_BREAK);
        System.out.println(COMMAND_ERROR);
        System.out.println(LINE_BREAK);
    }

    public static void printDescriptionErrorMessage() {
        System.out.println(LINE_BREAK);
        System.out.println(DESCRIPTION_ERROR);
        System.out.println(LINE_BREAK);
    }

    public static void processUserInput(TaskList tasks, Scanner in) {
        while (true) {
            try {
                String line = in.nextLine();
                Command command = Command.getFirstWord(line);

                if (command == null) {
                    continue;
                }
                switch (command) {
                case BYE:
                    Save.saveToLocalDisk(tasks);
                    return;
                case LIST:
                    printTaskListToConsole(tasks);
                    break;
                case MARK:
                    printMarkedItemToConsole(tasks, line);
                    break;
                case UNMARK:
                    printUnmarkedItemToConsole(tasks, line);
                    break;
                case DELETE:
                    printDeletedTaskToConsole(tasks, line);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    printNewTaskToConsole(tasks, line, command);
                    break;
                case SAVE:
                    Save.saveToLocalDisk(tasks);
                    break;
                }
            } catch (MissingFieldException e) {
                printDescriptionErrorMessage();
            }
        }
    }

    private static void printNewTaskToConsole(TaskList tasks, String line, Command command) throws MissingFieldException {
        String description = Command.getDescription(line);
        if (description.trim().isEmpty()) {
            throw new MissingFieldException();
        }
        printTaskToConsole(tasks, TaskType.valueOf(command.name()), description);
    }

    private static void printTaskListToConsole(TaskList tasks) {
        System.out.println(Console.LINE_BREAK);
        tasks.printTasks();
        System.out.println(Console.LINE_BREAK);
    }

    private static void printMarkedItemToConsole(TaskList tasks, String line) {
        System.out.println(Console.LINE_BREAK);
        tasks.markTask(line);
        System.out.println(Console.LINE_BREAK);
    }

    private static void printUnmarkedItemToConsole(TaskList tasks, String line) {
        System.out.println(Console.LINE_BREAK);
        tasks.unmarkTask(line);
        System.out.println(Console.LINE_BREAK);
    }

    private static void printTaskToConsole(TaskList tasks, TaskType todo, String description) {
        System.out.println(Console.LINE_BREAK);
        tasks.addTask(todo, description);
        System.out.println(Console.LINE_BREAK);
    }

    private static void printDeletedTaskToConsole(TaskList tasks, String line) {
        System.out.println(Console.LINE_BREAK);
        tasks.deleteFromTaskList(line);
        System.out.println(Console.LINE_BREAK);
    }

    public static void printGoodbyeMessage() {
        System.out.println(LINE_BREAK);
        System.out.println(GOODBYE);
        System.out.println(LINE_BREAK);
    }
}
