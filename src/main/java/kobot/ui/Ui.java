package kobot.ui;

import kobot.task.Task;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "Kobot";
    private static final String LOGO = "#########################################\n"
            + "##     _   __      _           _       ##\n"
            + "##    | | / /     | |         | |      ##\n"
            + "##    | |/ /  ___ | |__   ___ | |_     ##\n"
            + "##    |    \\ / _ \\| '_ \\ / _ \\| __|    ##\n"
            + "##    | |\\  \\ (_) | |_) | (_) | |_     ##\n"
            + "##    \\_| \\_/\\___/|_.__/ \\___/ \\__|    ##\n"
            + "##                                     ##\n"
            + "#########################################\n";

    private static final String LINE_DIVIDER = "-------------------------------------------";
    private static final String GOODBYE_MESSAGE = "Bye, hope to see you again!";

    private static final String TODO_COMMAND_FORMAT = "todo <description>";
    private static final String DEADLINE_COMMAND_FORMAT = "deadline <description> /by <datetime>";
    private static final String EVENT_COMMAND_FORMAT = "event <description> /from <datetime> /to <datetime>";
    private static final String MARK_TASK_COMMAND_FORMAT = "mark <task index>";
    private static final String UNMARK_TASK_COMMAND_FORMAT = "unmark <task index>";
    private static final String DELETE_TASK_COMMAND_FORMAT = "delete <task index>";
    
    private static final String INVALID_COMMAND_ERROR = "Invalid command. Please try again.";
    private static final String EMPTY_ARGUMENT_ERROR = "Empty or whitespace-only fields are not allowed.";
    private static final String MISSING_ARGUMENT_ERROR = "Missing information. Please try again.";
    
    private static final String INVALID_STORAGE_ENTRY_ERROR = "Invalid entry found in storage. Entry will be ignored.";
    private static final String CREATE_FILE_ERROR = "Error: Unable to create storage file.";
    private static final String UPDATE_FILE_ERROR = "Error: Unable to update storage file.";

    public static void printLineDivider() {
        System.out.println(LINE_DIVIDER);
    }
    public static void printHelloMessage() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm " + BOT_NAME + ". How may I assist you?");
    }

    public static void printGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }
    
    public static String receiveInput() throws NoSuchElementException {
        printLineDivider();
        System.out.print("> ");
        String input = "";
        
        try {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
        } catch (NoSuchElementException exception) {
            System.exit(0);
        }
        
        printLineDivider();
        return input;
    }
    
    public static void printAddTaskSuccessMessage(String taskType, Task task) {
        System.out.println(taskType + " has been added to list: " + task);
    }

    public static void printEmptyArgumentErrorMessage() {
        System.out.println(EMPTY_ARGUMENT_ERROR);
    }

    public static void printMissingArgumentErrorMessage() {
        System.out.println(MISSING_ARGUMENT_ERROR);
    }
    
    public static void printIndexOutOfBoundsMessage(String action) {
        System.out.println("Invalid index. Failed to " + action + " item.");
    }

    public static void printInvalidCommandErrorMessage() {
        System.out.println(INVALID_COMMAND_ERROR);
    }
    
    public static void printInvalidStorageEntryErrorMessage() {
        System.out.println(INVALID_STORAGE_ENTRY_ERROR);
    }

    public static void printCreateFileErrorMessage() {
        System.out.println(CREATE_FILE_ERROR);
    }

    public static void printUpdateFileErrorMessage() {
        System.out.println(UPDATE_FILE_ERROR);
    }

    public static void printToDoCommandUsage() {
        System.out.println("Command to add a new to-do task:");
        System.out.println(TODO_COMMAND_FORMAT);
    }

    public static void printDeadlineCommandUsage() {
        System.out.println("Command to add a new deadline:");
        System.out.println(DEADLINE_COMMAND_FORMAT);
    }

    public static void printEventCommandUsage() {
        System.out.println("Command to add a new event:");
        System.out.println(EVENT_COMMAND_FORMAT);
    }
    
    public static void printMarkCommandUsage() {
        System.out.println("Command to mark task as completed:");
        System.out.println(MARK_TASK_COMMAND_FORMAT);
    }

    public static void printUnmarkCommandUsage() {
        System.out.println("Command to mark task as not completed:");
        System.out.println(UNMARK_TASK_COMMAND_FORMAT);
    }

    public static void printDeleteCommandUsage() {
        System.out.println("Command to delete task:");
        System.out.println(DELETE_TASK_COMMAND_FORMAT);
    }
}