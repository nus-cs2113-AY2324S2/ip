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
     * 6. `deadline` <description> - Creates a new deadline task.
     * 7. `event` <description> - Creates a new event task.
     */
    public static Status processCommand(String userInput) {
        if (Parser.isUserInputEmpty(userInput)){
            System.out.println("Empty input");
            return Status.STATUS_OK;
        }

        String command = Parser.getCommand(userInput);
        String argument = Parser.getCommandArgument(userInput);
        int taskNumber;
        Status executionStatus = Status.STATUS_OK;

        System.out.println(Ui.SECTION_BAR);
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
            if (Parser.isValidTaskNumberString(argument)){
                taskNumber = Parser.getTaskNumberFromString(argument);
                TaskManager.markTaskAsDone(taskNumber);
            } else {
                System.out.println("Invalid argument for mark");
            }
            executionStatus = Status.STATUS_OK;
            break;
        case "unmark":
            if (Parser.isValidTaskNumberString(argument)) {
                taskNumber = Parser.getTaskNumberFromString(argument);
                TaskManager.markTaskAsUndone(taskNumber);
            } else {
                System.out.println("Invalid argument for unmark");
            }
            executionStatus = Status.STATUS_OK;
            break;
        case "todo":
            TaskManager.createNewTask(argument, TaskType.TODO);
            executionStatus = Status.STATUS_OK;
            break;
        case "deadline":
            TaskManager.createNewTask(argument, TaskType.DEADLINE);
            executionStatus = Status.STATUS_OK;
            break;
        case "event":
            TaskManager.createNewTask(argument, TaskType.EVENT);
            executionStatus = Status.STATUS_OK;
            break;
        default:
            System.out.println("Invalid Command.");
            executionStatus = Status.STATUS_OK;
            break;
        }
        System.out.println(Ui.SECTION_BAR + System.lineSeparator());
        return executionStatus;
    }
}
