import java.util.Scanner;

/**
 * Parses user commands and returns corresponding command objects.
 */
public class Parser {
    private static Ui ui = new Ui();
    private Storage storage;
    private TaskList tasks;

    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            command = scanner.nextLine();
            String taskType;

            // Checks if command contains a space
            if (command.contains(" ")) {
                taskType = command.substring(0, command.indexOf(" "));
            } else {
                taskType = command;
            }

            switch (taskType) {
            case "bye":
                ui.showBye();
                scanner.close();
                return; // Exit the method or return from the function
            case "list":
                tasks.printTaskList();
                break;
            case "mark":
                tasks.markTaskAsDone(command);
                storage.saveTasksToFile(tasks);
                break;
            case "unmark":
                tasks.unmarkTaskAsDone(command);
                storage.saveTasksToFile(tasks);
                break;
            case "todo":
                tasks.handleTodoCommand(command);
                storage.saveTasksToFile(tasks);
                break;
            case "deadline":
                tasks.handleDeadlineCommand(command);
                storage.saveTasksToFile(tasks);
                break;
            case "event":
                tasks.handleEventCommand(command);
                storage.saveTasksToFile(tasks);
                break;
            case "delete":
                tasks.deleteTask(command);
                storage.saveTasksToFile(tasks);
                break;
            default:
                ui.showInvalidCommand();
            }
        }
    }


}
