package cody.parser;

import cody.TaskList;
import cody.CodyException;
import cody.ui.Ui;

/**
 * The Parser class is responsible for parsing user input and executing the corresponding commands.
 */
public class Parser {

    /**
     * Parses and executes a command based on the user input.
     *
     * @param input    The user input string containing the command and any arguments.
     * @param taskList The TaskList object on which the command will be executed.
     * @return A string representing the result of the command execution.
     * @throws CodyException If an error occurs during command parsing or execution.
     */
    public static String parseCommand(String input, TaskList taskList) throws CodyException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        try {
            switch (command) {
            case "list":
                return taskList.printList();
            case "delete":
                return taskList.deleteTask(Integer.parseInt(argument));
            case "mark":
            case "unmark":
                return taskList.handleMarking(command, Integer.parseInt(argument));
            case "find":
                return taskList.findTask(argument);
            case "help":
                return Ui.HELP_MESSAGE;
            default:
                return taskList.addTask(input);
            }
        } catch (NumberFormatException e) {
            throw new CodyException("Invalid task number. Please use a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new CodyException("Task number out of bounds. Please choose a number within the list.");
        }
    }
}
