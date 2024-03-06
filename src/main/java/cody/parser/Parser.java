package cody.parser;

import cody.CodyException;
import cody.TaskList;
import cody.ui.Ui;

public class Parser {

    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

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
            return Ui.HELP_MESSAGE;
        default:
            return taskList.addTask(input);
        }
    }

    private String handleListCommand(String argument) throws CodyException {
        if (!argument.isEmpty()) {
            throw new CodyException("The 'list' command does not take any arguments.");
        }
        return taskList.printList();
    }

    private String handleDeleteCommand(String argument) throws CodyException {
        int taskNumber = parseTaskNumber(argument);
        return taskList.deleteTask(taskNumber);
    }

    private String handleMarkCommand(String argument) throws CodyException {
        int taskNumber = parseTaskNumber(argument);
        return taskList.handleMarking("mark", taskNumber);
    }

    private String handleUnmarkCommand(String argument) throws CodyException {
        int taskNumber = parseTaskNumber(argument);
        return taskList.handleMarking("unmark", taskNumber);
    }

    private String handleFindCommand(String argument) throws CodyException {
        if (argument.isEmpty()) {
            throw new CodyException("The 'find' command requires a keyword to search for.");
        }
        return taskList.findTask(argument);
    }

    private int parseTaskNumber(String argument) throws CodyException {
        try {
            return Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new CodyException("Invalid task number. Please use a number.");
        }
    }
}
