package nyanbot.tool;

import nyanbot.task.Task;

import java.util.ArrayList;

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
    private static boolean isRunning = true;

    public static String[] parseCommand(String input) {
        String[] splitInputs = input.split(" ");
        splitInputs[0] = splitInputs[0].toUpperCase();
        return splitInputs;
    }

    public static boolean getStatus() {
        return isRunning;
    }

    public static void processCommand(String input, TaskList tasks) {
        String[] commands = Parser.parseCommand(input);
        switch (commands[0]) {
            case BYE_COMMAND:
                isRunning = false;
                break;
            case DELETE_COMMAND:
                tasks.deleteTask(input);
                break;
            case LIST_COMMAND:
                ArrayList<Task> importedTasks = tasks.exportTask();
                UI.printTasks(importedTasks);
                break;
            case MARK_COMMAND:
                tasks.markTask(input);
                break;
            case UNMARK_COMMAND:
                tasks.unmarkTask(input);
                break;
            case TODO_COMMAND:
                tasks.addTodo(input);
                break;
            case DEADLINE_COMMAND:
                tasks.addDeadline(input);
                break;
            case EVENT_COMMAND:
                tasks.addEvent(input);
                break;
            case HELP_COMMAND:
                UI.printSike();
                break;
            default:
                UI.printInvalidInput();
                break;
        }
    }
}
