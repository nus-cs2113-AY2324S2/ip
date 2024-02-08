import task.TaskManager;

public class CommandManager {
    /**
     * Takes user inputs as commands and process them.
     * If the input is not one of the supported commands below, the input shall be seen as a new task.
     * Tasks are NOT saved when the program exits.
     * Possible commands:
     * 1. `bye`, `exit` - Exits the program.
     * 2. `list` - Lists out all the tasks.
     * 3. `mark <task_number> - Marks specific task as done.
     * 4. `unmark` <task_number> - Marks specific task as undone.
     */
    public static Status processCommand(String userInput) {
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
            if (commands.length >= 2) {
                taskNumber = Integer.parseInt(commands[1]);
                TaskManager.markTaskAsDone(taskNumber);
            }
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        case "unmark":
            System.out.println(Ui.SECTION_BAR);
            if (commands.length >= 2) {
                taskNumber = Integer.parseInt(commands[1]);
                TaskManager.markTaskAsUndone(taskNumber);
            }
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        default:
            TaskManager.createNewTask(userInput);
            System.out.println(Ui.SECTION_BAR);
            System.out.println("Added: " + userInput);
            System.out.println(Ui.SECTION_BAR + "\n");
            return Status.STATUS_OK;
        }
    }
}
