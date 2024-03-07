package cody.parser;

import cody.CodyException;
import cody.TaskList;
import cody.ui.Ui;

public class Parser {
    private static final String OUT_OF_BOUNDS_MESSAGE = "Task number out of bounds. "
            + "Please choose a number within the list";
    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses and executes a command based on the user input.
     *
     * @param input The user input string containing the command and any arguments.
     * @return A string representing the result of the command execution.
     * @throws CodyException If an error occurs during command parsing or execution.
     */
    public String parseCommand(String input) throws CodyException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "list":
            return handleListCommand(argument);
        case "delete":
            return handleDeleteCommand(argument);
        case "mark":
            return handleMarkCommand(argument);
        case "unmark":
            return handleUnmarkCommand(argument);
        case "find":
            return handleFindCommand(argument);
        case "help":
            return handleHelpCommand(argument);
        default:
            return taskList.addTask(input);
        }
    }

    private String handleListCommand(String argument) throws CodyException {
        if (!argument.isEmpty()) {
            throw new CodyException("The 'list' command does not take any arguments");
        }
        return taskList.printList();
    }

    private String handleDeleteCommand(String argument) throws CodyException {
        int taskNumber = parseTaskNumber(argument);
        try {
            return taskList.deleteTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new CodyException(OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private String handleMarkCommand(String argument) throws CodyException {
        int taskNumber = parseTaskNumber(argument);
        try {
            return taskList.handleMarking("mark", taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new CodyException(OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private String handleUnmarkCommand(String argument) throws CodyException {
        int taskNumber = parseTaskNumber(argument);
        try {
            return taskList.handleMarking("unmark", taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new CodyException(OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private String handleFindCommand(String argument) throws CodyException {
        if (argument.isEmpty()) {
            throw new CodyException("The 'find' command requires a keyword to search for");
        }
        return taskList.findTask(argument);
    }

    private String handleHelpCommand(String argument) throws CodyException {
        if (!argument.isEmpty()) {
            throw new CodyException("The 'help' command does not take any arguments");
        }
        return Ui.HELP_MESSAGE;
    }

    private int parseTaskNumber(String argument) throws CodyException {
        try {
            return Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new CodyException("Invalid task number. Please use a number");
        }
    }
}
