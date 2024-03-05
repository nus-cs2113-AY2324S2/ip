package cody.parser;

import cody.TaskList;
import cody.CodyException;

public class Parser {

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
            default:
                return taskList.addTask(input);
            }
        } catch (NumberFormatException e) {
            throw new CodyException("Invalid task number. Please use a number");
        } catch (IndexOutOfBoundsException e) {
            throw new CodyException("Task number out of bounds. Please choose a number within the list");
        }
    }
}
