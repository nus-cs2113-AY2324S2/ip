package ava.parser;

import ava.storage.Storage;
import ava.tasklist.TaskList;
import ava.ui.Ui;
import ava.exception.EmptyTaskNameException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private boolean isExit;

    public Parser() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Decides which command is wanted, then executes the command desired.
     */
    public void parseCommand(String command, TaskList tasks, Storage storage, Ui ui) {
        if (command.equals("bye")) {
            isExit = true;
        } else if (command.equals("list")) {
            ui.displayTask(tasks);
        } else if (command.contains("mark")) {
            tasks.markTask(command);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            try {
                tasks.addTask(command, extractTypeForCommand(command));
                ui.printAfterAddingTask(tasks);
            } catch (EmptyTaskNameException e) {
                ui.printEmptyTaskNameExceptionMessage(extractTypeForCommand(command));
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printDateFormatExceptionMessage();
            }
        } else if (command.startsWith("delete")) {
            tasks.deleteTask(command);
        } else {
            ui.printUnknownCommandExceptionMessage();
        }
        storage.saveTasks(tasks);
    }

    /**
     * Returns type of the command entered.
     */
    public static String extractTypeForCommand(String command) {
        String[] taskAndDescription = command.split(" ");
        return taskAndDescription[0];
    }

    /**
     * Returns the task number specified by the user.
     */
    public static int extractTaskNumber(String type, String command) {
        String taskNumber = command.replace(type, "");
        return Integer.parseInt(taskNumber) - 1;
    }
}
