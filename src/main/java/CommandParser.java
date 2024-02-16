public class CommandParser {
    private final TaskList taskList;


    public CommandParser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void parseCommand(String userInput) throws HandleException {
        if (userInput.trim().isEmpty()) {
            throw new HandleException("The input cannot be empty!");
        } else if (userInput.equalsIgnoreCase("list")) {
            taskList.listTasks();
        } else if (!userInput.startsWith("todo") && !userInput.startsWith("deadline") && !userInput.startsWith("event")) {
            throw new HandleException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            taskList.addTask(userInput);
        }
    }
}

