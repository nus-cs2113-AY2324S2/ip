package bean;

import java.util.Scanner;

import bean.command.Parser;
import bean.command.exception.NoValueException;
import bean.task.Task;
import bean.task.TaskList;

public class Bean {
    public static final String MESSAGE_TASK_UNDONE = "    Oops, looks like you're still not done with this:\n   ";
    public static final String MESSAGE_TASK_ADDED = "    Hey, I've added:\n    ";
    private static final String SEPARATOR = "   -------------------------------------------------";
    public static final String MESSAGE_WELCOME = "    Hello! I'm Bean.\n    What can I do for you?";
    public static final String MESSAGE_LIST_HEADER = "    These are the tasks in your list:";
    public static final String MESSAGE_TASK_DONE = "    Hey, looks like you're done with this task:\n   ";
    public static final String MESSAGE_CURRENT_NUMTASKS = "    You currently have ";
    public static final String EXCEPTION_INVALID_TASK_NUMBER = "    Sorry, that was an invalid task number.";
    public static final String EXCEPTION_NO_VALUE_FOR_REQUIRED_FIELDS = "    Exception: no value for required fields";
    public static final String EXCEPTION_NO_SUCH_COMMAND = "    Sorry, I don't understand that command";
    public static final String MESSAGE_GOODBYE = "    Bean will take a nap now. Bye!";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
        System.out.println(SEPARATOR);
    }

    private static void printTaskList(TaskList tasks) {
        System.out.println(MESSAGE_LIST_HEADER);
        System.out.println(tasks.toString());
        System.out.println(SEPARATOR);
    }

    private static void printTaskDone(Task task) {
        System.out.print(MESSAGE_TASK_DONE);
        System.out.println(task.toString());
        System.out.println(SEPARATOR);
    }

    private static void printTaskUndone(Task task) {
        System.out.print(MESSAGE_TASK_UNDONE);
        System.out.println(task.toString());
        System.out.println(SEPARATOR);
    }

    private static void printTaskAdded(Task task, int numTasks) {
        System.out.println(MESSAGE_TASK_ADDED + task.toString());
        String singularOrPlural = numTasks == 1 ? " task."  : " tasks.";
        System.out.println(MESSAGE_CURRENT_NUMTASKS + numTasks + singularOrPlural);
        System.out.println(SEPARATOR);
    }

    private static void printInvalidTaskNo() {
        System.out.println(EXCEPTION_INVALID_TASK_NUMBER);
        System.out.println(SEPARATOR);
    }

    private static void printNoValueForFields() {
        System.out.println(EXCEPTION_NO_VALUE_FOR_REQUIRED_FIELDS);
        System.out.println(SEPARATOR);
    }

    private static void printNoSuchCommand() {
        System.out.println(EXCEPTION_NO_SUCH_COMMAND);
        System.out.println(SEPARATOR);
    }

    private static void printGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(SEPARATOR);
    }

    private static void processAndExecute(String line, TaskList listOfTasks) {
        Parser userLine = new Parser(line);
        String command = userLine.getCommand();
        switch (command) {
        case "list": {
            printTaskList(listOfTasks);
            break;
        }
        case "mark": {
            try {
                int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
                Task markedTask = listOfTasks.markTask(taskIndex, true);
                printTaskDone(markedTask);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                printInvalidTaskNo();
            }
            break;
        }
        case "unmark": {
            try {
                int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
                Task unmarkedTask = listOfTasks.markTask(taskIndex, false);
                printTaskUndone(unmarkedTask);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                printInvalidTaskNo();
            }
            break;
        }
        case "todo": {
            String description = userLine.getArgument();
            try {
                Task newTask = listOfTasks.addTask(description);
                printTaskAdded(newTask, listOfTasks.getNumTasks());
            } catch (NoValueException e) {
                printNoValueForFields();
            }
            break;
        }
        case "deadline": {
            String description = userLine.getArgument();
            String by = userLine.getValue("by");
            try {
                Task newTask = listOfTasks.addTask(description, by);
                printTaskAdded(newTask, listOfTasks.getNumTasks());
            } catch (NoValueException e) {
                printNoValueForFields();
            }
            break;
        }
        case "event": {
            String description = userLine.getArgument();
            String start = userLine.getValue("start");
            String end = userLine.getValue("end");
            try {
                Task newTask = listOfTasks.addTask(description, start, end);
                printTaskAdded(newTask, listOfTasks.getNumTasks());
            } catch (NoValueException e) {
                printNoValueForFields();
            }
            break;
        }
        default:
            printNoSuchCommand();
            break;
        }
    }

    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine.trim();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        String line = getUserInput();
        TaskList listOfTasks = new TaskList();

        while (!line.equals("bye")) {
            processAndExecute(line, listOfTasks);
            line = getUserInput();
        }

        printGoodbyeMessage();
    }
}
