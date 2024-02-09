import misc.Parser;
import task.Task.TaskType;
import task.TaskManager;

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
    public static Status processCommand(String userInput) {
        if (userInput.isEmpty()) {
            System.out.println("Error: Empty input");
            return Status.STATUS_ERROR_EMPTY_INPUT;
        }

        String command = Parser.getCommand(userInput);
        String rawArgument = Parser.getCommandArgument(userInput);
        Status executionStatus;

        Ui.printSectionBar(false);
        switch (command) {
        case "exit":
            // fallthrough
        case "bye":
            Ui.printGoodbye();
            executionStatus = Status.STATUS_EXIT;
            break;
        case "list":
            TaskManager.printTaskList();
            executionStatus = Status.STATUS_OK;
            break;
        case "mark":
            processMarkCommand(rawArgument);
            executionStatus = Status.STATUS_OK;
            break;
        case "unmark":
            processUnmarkCommand(rawArgument);
            executionStatus = Status.STATUS_OK;
            break;
        case "todo":
            TaskManager.createNewTask(rawArgument, TaskType.TODO);
            executionStatus = Status.STATUS_OK;
            break;
        case "deadline":
            TaskManager.createNewTask(rawArgument, TaskType.DEADLINE);
            executionStatus = Status.STATUS_OK;
            break;
        case "event":
            TaskManager.createNewTask(rawArgument, TaskType.EVENT);
            executionStatus = Status.STATUS_OK;
            break;
        default:
            System.out.println("Invalid Command.");
            executionStatus = Status.STATUS_ERROR_INVALID_COMMAND;
            break;
        }
        Ui.printSectionBar(true);
        return executionStatus;
    }

    private static void processMarkCommand(String rawArgument) {
        if (Parser.isValidTaskNumberString(rawArgument)) {
            int taskNumber = Parser.getTaskNumberFromString(rawArgument);
            TaskManager.markTaskAsDone(taskNumber);
        } else {
            System.out.println("Invalid argument for mark");
        }
    }

    private static void processUnmarkCommand(String rawArgument) {
        if (Parser.isValidTaskNumberString(rawArgument)) {
            int taskNumber = Parser.getTaskNumberFromString(rawArgument);
            TaskManager.markTaskAsUndone(taskNumber);
        } else {
            System.out.println("Invalid argument for unmark");
        }
    }
}
