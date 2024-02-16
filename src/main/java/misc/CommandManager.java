package misc;

import anonbot.Ui;
import exception.IncompleteCommandException;
import exception.InvalidTaskException;
import task.Task.TaskType;
import task.TaskManager;
import exception.InvalidCommandException;

public class CommandManager {
    /**
     * Takes user inputs as commands and process them.
     * If the input is not one of the supported commands below, NO new task will be created.
     * Tasks are NOT saved when the program exits.
     * Possible commands:
     * 1. `bye`, `exit` - Exits the program.
     * 2. `list` - Lists out all the tasks.
     * 3. `mark <task_number> - Marks specific task as done.
     * 4. `unmark` <task_number> - Marks specific task as undone.
     * 5. `todo` <description> - Creates a new todo task.
     * 6. `deadline` <description> /by <end_time> - Creates a new deadline task.
     * 7. `event` <description> /from <start_time> /to <end_time> - Creates a new event task.
     */
    public static Status processCommand(String userInput) throws InvalidCommandException {
        Status executionStatus = Status.STATUS_OK;

        if (userInput.isEmpty()) {
            throw new InvalidCommandException("");
        }

        String command = Parser.getCommand(userInput);
        String rawArgument = Parser.getCommandArgument(userInput);

        try {
            switch (command) {
            case "exit":
                // fallthrough
            case "bye":
                Ui.printGoodbye();
                executionStatus = Status.STATUS_EXIT;
                break;
            case "list":
                TaskManager.printTaskList();
                break;
            case "mark":
                processMarkCommand(rawArgument);
                break;
            case "unmark":
                processUnmarkCommand(rawArgument);
                break;
            case "todo":
                TaskManager.createNewTask(rawArgument, TaskType.TODO);
                break;
            case "deadline":
                TaskManager.createNewTask(rawArgument, TaskType.DEADLINE);
                break;
            case "event":
                TaskManager.createNewTask(rawArgument, TaskType.EVENT);
                break;
            default: // Invalid Command
                throw new InvalidCommandException(command);
            }
        } catch (IncompleteCommandException e) {
            e.printErrorMessage();
        } catch (InvalidTaskException e) {
            e.printErrorMessage();
        }
        return executionStatus;
    }

    private static void processMarkCommand(String rawArgument) throws IncompleteCommandException {
        try {
            int taskNumber = Parser.getTaskNumberFromString(rawArgument);
            TaskManager.markTaskAsDone(taskNumber);
        } catch (NumberFormatException e) {
            throw new IncompleteCommandException("mark", rawArgument);
        }
    }

    private static void processUnmarkCommand(String rawArgument) throws IncompleteCommandException {
        try {
            int taskNumber = Parser.getTaskNumberFromString(rawArgument);
            TaskManager.markTaskAsUndone(taskNumber);
        } catch (NumberFormatException e) {
            throw new IncompleteCommandException("unmark", rawArgument);
        }
    }
}
