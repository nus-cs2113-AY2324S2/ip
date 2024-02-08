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

        switch (command) {
        case "exit":
            // fallthrough
        case "bye":
            return Status.STATUS_EXIT;
        case "list":
            System.out.println(Ui.SECTION_BAR);
            TaskManager.printTaskList();
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        case "mark":
            System.out.println(Ui.SECTION_BAR);
            if (Parser.isValidTaskNumberString(argument)){
                taskNumber = Parser.getTaskNumberFromString(argument);
                TaskManager.markTaskAsDone(taskNumber);
            } else {
                System.out.println("Invalid argument for mark");
            }
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        case "unmark":
            System.out.println(Ui.SECTION_BAR);
            if (Parser.isValidTaskNumberString(argument)) {
                taskNumber = Parser.getTaskNumberFromString(argument);
                TaskManager.markTaskAsUndone(taskNumber);
            } else {
                System.out.println("Invalid argument for unmark");
            }
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        case "todo":
            System.out.println(Ui.SECTION_BAR);
            TaskManager.createNewTask(argument, TaskType.TODO);
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        case "deadline":
            System.out.println(Ui.SECTION_BAR);
            TaskManager.createNewTask(argument, TaskType.DEADLINE);
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        case "event":
            System.out.println(Ui.SECTION_BAR);
            TaskManager.createNewTask(argument, TaskType.EVENT);
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        default:
            System.out.println(Ui.SECTION_BAR);
            System.out.println("Invalid Command.");
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        }
    }
}
