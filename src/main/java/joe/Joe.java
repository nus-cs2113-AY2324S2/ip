package joe;

import joe.task.TaskType;
import joe.util.FileManager;
import joe.util.InputParser;
import joe.util.Printer;
import joe.task.TaskManager;

import java.io.IOException;
import java.util.Scanner;

public class Joe {
    protected static final String EXIT_COMMAND = "bye";
    protected static final String LIST_COMMAND = "list";
    protected static final String MARK_COMMAND = "mark";
    protected static final String UNMARK_COMMAND = "unmark";
    protected static final String NEW_TODO_COMMAND = "todo";
    protected static final String NEW_DEADLINE_COMMAND = "deadline";
    protected static final String NEW_EVENT_COMMAND = "event";
    protected static final String DELETE_COMMAND = "delete";

    public static void main(String[] args) {
        Printer.printGreeting();
        runJoe();
        Printer.printExitMessage();
    }

    private static void runJoe() {
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        try {
            FileManager.loadData(taskManager);
        } catch (IOException e) {
            Printer.printLoadError();
        }

        boolean hasExitInput;
        do {
            String input = in.nextLine();
            input = input.trim();
            String command = InputParser.getCommand(input);
            String message = InputParser.getMessage(input);
            hasExitInput = handleCommand(command, message, taskManager);
        } while (!hasExitInput);

        in.close();
    }

    private static boolean handleCommand(String command, String message, TaskManager taskManager) {
        boolean hasExitInput = false;
        switch (command) {
        case EXIT_COMMAND:
            if (!message.isEmpty()) {
                Printer.printExitError();
                break;
            }
            hasExitInput = true;
            break;
        case LIST_COMMAND:
            if (!message.isEmpty()) {
                Printer.printListError();
                break;
            }
            taskManager.listTasks();
            break;
        case MARK_COMMAND:
        case UNMARK_COMMAND:
            try {
                int taskNumber = Integer.parseInt(message);
                taskManager.toggleTaskMarkedStatus(taskNumber, command.equals(MARK_COMMAND));
            } catch (NumberFormatException | JoeException e) {
                Printer.printInvalidMarkError();
            }
            break;
        case NEW_TODO_COMMAND:
            try {
                taskManager.addTask(TaskType.TODO, message);
            } catch (JoeException e) {
                Printer.printToDoEmptyError();
            }
            break;
        case NEW_DEADLINE_COMMAND:
            try {
                taskManager.addTask(TaskType.DEADLINE, message);
            } catch (JoeException e) {
                Printer.printDeadlineInputError();
            }
            break;
        case NEW_EVENT_COMMAND:
            try {
                taskManager.addTask(TaskType.EVENT, message);
            } catch (JoeException e) {
                Printer.printEventInputError();
            }
            break;
        case DELETE_COMMAND:
            try {
                int taskNumber = Integer.parseInt(message);
                taskManager.deleteTask(taskNumber);
            } catch (NumberFormatException | JoeException e) {
                Printer.printDeleteError();
            }
            break;
        default:
            Printer.printDefaultError();
            break;
        }

        return hasExitInput;
    }
}
