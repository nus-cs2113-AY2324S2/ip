public class Parser {
    public static void parseInput(String userInput, TaskList taskList, Ui ui) throws DukeException {
        if (userInput.equalsIgnoreCase("bye")) {
            ui.showByeMessage();
            Storage.saveTasksToFile(taskList.getTasks());
            System.exit(0);
        } else if (userInput.equalsIgnoreCase("list")) {
            taskList.displayTasks();
        } else if (userInput.startsWith("todo ")) {
            taskList.addTodoTask(userInput.substring(5));
        } else if (userInput.startsWith("deadline ")) {
            taskList.addDeadlineTask(userInput.substring(9));
        } else if (userInput.startsWith("event ")) {
            taskList.addEventTask(userInput.substring(6));
        } else if (userInput.startsWith("mark ")) {
            taskList.markTask(userInput);
        } else if (userInput.startsWith("unmark ")) {
            taskList.unmarkTask(userInput);
        } else if (userInput.startsWith("delete ")) {
            taskList.deleteTask(userInput);
        } else if (userInput.startsWith("find ")) {
            taskList.findTasks(userInput.substring(5));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
    }
}
