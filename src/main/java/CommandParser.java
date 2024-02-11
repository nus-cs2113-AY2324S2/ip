public class CommandParser {
    private final TaskList taskList;

    public CommandParser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void parseCommand(String userInput) {
        if (userInput.equalsIgnoreCase("list")) {
            taskList.listTasks();
        } else {
            taskList.addTask(userInput);
        }
    }
}

