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
        } else if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ", 2);
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new HandleException("OOPS!!! The task number to delete cannot be empty.");
            }
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new HandleException("OOPS!!! The task number is invalid.");
            }
            taskList.deleteTask(taskIndex);
        } else if (!userInput.startsWith("todo") && !userInput.startsWith("deadline") && !userInput.startsWith("event")) {
            throw new HandleException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            taskList.addTask(userInput);
        }
    }
}


