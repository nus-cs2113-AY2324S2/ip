public class CommandParser {
    private final TaskList taskList;
    private final Ui ui;

    public CommandParser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void parseCommand(String userInput) {
        try {
            if (userInput.trim().isEmpty()) {
                ui.showError("The input cannot be empty!");
            } else if (userInput.equalsIgnoreCase("list")) {
                taskList.listTasks();
            } else if (userInput.startsWith("delete")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get task index
                taskList.deleteTask(taskIndex);
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get task index
                taskList.markTaskAsDone(taskIndex);
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get task index
                taskList.markTaskAsNotDone(taskIndex);
            } else if (!userInput.startsWith("todo") && !userInput.startsWith("deadline") && !userInput.startsWith("event")) {
                ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } else {
                taskList.addTask(userInput);
            }
        } catch (HandleException he) {
            ui.showError(he.getMessage());
        } catch (NumberFormatException nfe) {
            ui.showError("OOPS!!! The task number is invalid.");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            ui.showError("OOPS!!! It seems like the command is not complete.");
        }
    }
}
