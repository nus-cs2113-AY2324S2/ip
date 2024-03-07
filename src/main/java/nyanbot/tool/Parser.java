package nyanbot.tool;

import nyanbot.exception.NyanException;
import nyanbot.task.Task;

import java.util.ArrayList;

/***
 * Stores collection of tasks and methods for manipulation of task list.
 */
public class Parser {
    private static final String LIST_COMMAND = "LIST";
    private static final String DELETE_COMMAND = "DELETE";
    private static final String MARK_COMMAND = "MARK";
    private static final String UNMARK_COMMAND = "UNMARK";
    private static final String TODO_COMMAND = "TODO";
    private static final String DEADLINE_COMMAND = "DEADLINE";
    private static final String EVENT_COMMAND = "EVENT";
    private static final String BYE_COMMAND = "BYE";
    private static final String HELP_COMMAND = "HELP";
    private static final String FIND_COMMAND = "FIND";
    private static boolean isRunning = true;

    /***
     * Parses user input into 2 tokens for further computation in processCommand method.
     * First token corresponds to task type and is capitalised for processCommand
     * Second token stores other information ie task status, description, date etc
     * @param input string containing user input
     * @return string array containing tokens from user input
     */
    public static String[] parseCommand(String input) {
        String[] splitInputs = input.split(" ", 2);
        splitInputs[0] = splitInputs[0].toUpperCase();
        return splitInputs;
    }

    /***
     *Returns boolean which determine whether programme should continue running or quit
     * @return boolean of programme status
     */
    public static boolean getStatus() {
        return isRunning;
    }

    /***
     *Processes user input then executes required commands within provided instance of TaskList
     * @param input string containing user input
     * @param tasks tasklist instance where tasks are stored and commands are executed on
     */
    public static void executeCommand(String input, TaskList tasks) {
        try {
            String[] commands = Parser.parseCommand(input);
            processCommand(tasks, commands);
        } catch (NyanException e) {
            UI.printNyanException(e.getMessage());
        }
    }

    private static void processCommand(TaskList tasks, String[] commands) throws NyanException {
        try {
            switch (commands[0]) {
                case BYE_COMMAND:
                    isRunning = false;
                    return;
                case LIST_COMMAND:
                    ArrayList<Task> importedTasks = tasks.exportTask();
                    UI.printTasks(importedTasks);
                    return;
                case HELP_COMMAND:
                    UI.printSike();
                    return;
                case DELETE_COMMAND:
                    tasks.deleteTask(commands[1]);
                    break;
                case MARK_COMMAND:
                    tasks.markTask(commands[1]);
                    break;
                case UNMARK_COMMAND:
                    tasks.unmarkTask(commands[1]);
                    break;
                case TODO_COMMAND:
                    tasks.addTodo(commands[1]);
                    break;
                case DEADLINE_COMMAND:
                    tasks.addDeadline(commands[1]);
                    break;
                case EVENT_COMMAND:
                    tasks.addEvent(commands[1]);
                    break;
                case FIND_COMMAND:
                    ArrayList<Task> foundTasks = tasks.findTasks(commands[1]);
                    UI.printTasks(foundTasks);
                    break;
                default:
                    UI.printInvalidInput();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NyanException("not enough arguments");
        }
    }
}
