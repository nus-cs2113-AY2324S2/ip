/**
 * Represents a parser utility for interpreting user input and executing corresponding actions.
 */
public class Parser {

    public static void parseInput(String input, TaskList taskList, Ui ui) {
        String[] command = input.split(" ", 2);
        switch (command[0]) {
            case "list":
                taskList.listTasks();
                break;
            case "mark":
                taskList.markTaskDone(Integer.parseInt(command[1]) - 1, ui);
                break;
            case "unmark":
                taskList.markTaskNotDone(Integer.parseInt(command[1]) - 1, ui);
                break;
            case "todo":
                if (command.length < 2 || command[1].trim().isEmpty()) {
                    ui.showError("Do not leave the description of a todo empty.");
                } else {
                    taskList.addTodoTask(command[1], ui);
                }
                break;
            case "deadline":
                if (command.length < 2 || !command[1].contains("/by")) {
                    ui.showError("I need you to give me the description and deadline (/by).");
                } else {
                    taskList.addDeadlineTask(command[1], ui);
                }
                break;
            case "event":
                taskList.addEventTask(command[1], ui);
                break;
            case "delete":
                taskList.deleteTask(Integer.parseInt(command[1]) - 1, ui);
                break;
            case "find":
                taskList.findTaskByKeyword(command[1], ui);
                break;
            case "bye":
                ui.showByeMessage();
                break;
            default:
                ui.showError("I'm sorry, but I don't know what that means :-(");
        }
    }
}


