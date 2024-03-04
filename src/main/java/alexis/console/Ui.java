package alexis.console;

import alexis.exception.MissingFieldException;
import alexis.task.TaskList;
import alexis.task.TaskType;

import java.util.Scanner;

/**
 * The Ui class handles all user interactions of Alexis. It is responsible for printing messages to the console,
 * processing user input, and executing commands based on the parsed input.
 */
public class Ui {
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
    private final static String GREETING = "Hello, I'm Alexis.\n" + "What can I do for you?";
    private final static String GOODBYE = "Bye. Hope to see you again soon!";
    private final static String INVALID_COMMAND_ERROR
            = "Sorry bro I don't quite get what you wanna do. Please enter a valid command.";
    private final static String MISSING_DESCRIPTION_ERROR
            = "Sorry bro I don't know what task you wanna add. Please add a description.";
    public final static String MISSING_FIND_DESCRIPTION_ERROR
            = "Sorry bro I don't know what task you are looking for. Please add a description.";
    public final static String NO_MATCHING_TASK_MESSAGE = "Sorry bro looks like this task doesnt exist.";
    public  final static String MISSING_DEADLINE_ERROR = "Please indicate the deadline of your task.";
    public final static String MISSING_EVENT_START_OR_END_DATE_ERROR
            = "Please indicate the start and end dates/times for your event properly.";
    public final static String LIST_MESSAGE = "Here are the tasks in your list:";
    public final static String MARK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public final static String MARK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    public final static String MATCHING_TASKS_MESSAGE = "Here are the matching tasks in your list:";
    public final static String MISSING_TASK_INDEX_MESSAGE = "Please tell me which task you are referring to.";
    public final static String INVALID_TASK_INDEX_MESSAGE = "Sorry, I can't find the task you are referring to.";
    public final static String ADD_MESSAGE = "Got it. I've added this task:";
    public final static String REMOVE_MESSAGE = "Noted. I've removed this task:";
    public final static String LIST_UPDATE_MESSAGE = "Now you have %d tasks in the list.\n";

    public static void printWelcomeMessage() {
        System.out.println(NAME);
        System.out.println(LINE_BREAK);
        System.out.println(GREETING);
        System.out.println(LINE_BREAK);
    }

    public static void printCommandErrorMessage() {
        System.out.println(LINE_BREAK);
        System.out.println(INVALID_COMMAND_ERROR);
        System.out.println(LINE_BREAK);
    }

    public static void printDescriptionErrorMessage() {
        System.out.println(LINE_BREAK);
        System.out.println(MISSING_DESCRIPTION_ERROR);
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a newly added task to the console.
     *
     * @param tasks The task list to add the new task into.
     * @param line The input line containing the command and description of the new task.
     * @param command The command indicating the task type to be added.
     * @throws MissingFieldException If the description is missing.
     */
    public static void printNewTaskToConsole(TaskList tasks, String line, Command command)
            throws MissingFieldException {
        String description = Parser.parseDescription(line);
        printTaskToConsole(tasks, TaskType.valueOf(command.name()), description);
    }

    public static void printTaskListToConsole(TaskList tasks) {
        System.out.println(LINE_BREAK);
        tasks.printTasks();
        System.out.println(LINE_BREAK);
    }

    public static void printMarkedItemToConsole(TaskList tasks, String line) {
        System.out.println(LINE_BREAK);
        tasks.markTask(line);
        System.out.println(LINE_BREAK);
    }

    public static void printUnmarkedItemToConsole(TaskList tasks, String line) {
        System.out.println(LINE_BREAK);
        tasks.unmarkTask(line);
        System.out.println(LINE_BREAK);
    }

    private static void printTaskToConsole(TaskList tasks, TaskType todo, String description) {
        System.out.println(LINE_BREAK);
        tasks.addTask(todo, description);
        System.out.println(LINE_BREAK);
    }

    public static void printMatchingTasksToConsole(TaskList tasks, String line) {
        System.out.println(LINE_BREAK);
        tasks.printMatchingTasksMessage(line);
        System.out.println(LINE_BREAK);
    }

    public static void printDeletedTaskToConsole(TaskList tasks, String line) {
        System.out.println(LINE_BREAK);
        tasks.deleteFromTaskList(line);
        System.out.println(LINE_BREAK);
    }

    public static void printGoodbyeMessage() {
        System.out.println(LINE_BREAK);
        System.out.println(GOODBYE);
        System.out.println(LINE_BREAK);
    }
}
