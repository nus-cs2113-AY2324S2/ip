import java.util.Scanner;

/**
 * Parses user commands and performs corresponding actions.
 */
public class Parser {
    private static Ui ui = new Ui();
    private Storage storage;
    private TaskList tasks;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a Parser with the given Storage and TaskList.
     *
     * @param storage The storage used to handle file operations.
     * @param tasks   The task list to be manipulated.
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Reads user commands and executes corresponding actions until the "bye" command is received.
     */
    public void readCommand() {
        String command;

        while (true) {
            command = scanner.nextLine();
            String taskType = command.contains(" ") ? command.substring(0, command.indexOf(" ")) : command;

            switch (taskType) {
            case "bye":
                handleByeCommand();
                return;
            case "list":
                handleListCommand();
                break;
            case "mark":
                handleMarkCommand(command);
                break;
            case "unmark":
                handleUnmarkCommand(command);
                break;
            case "todo":
                handleTodoCommand(command);
                break;
            case "deadline":
                handleDeadlineCommand(command);
                break;
            case "event":
                handleEventCommand(command);
                break;
            case "delete":
                handleDeleteCommand(command);
                break;
            case "find":
                handleFindCommand(command);
                break;
            default:
                ui.showInvalidCommand();
            }
        }
    }

    private void handleByeCommand() {
        ui.showBye();
        scanner.close();
    }

    private void handleListCommand() {
        tasks.printTaskList();
    }

    private void handleMarkCommand(String command) {
        tasks.markTaskAsDone(command);
        storage.saveTasksToFile(tasks);
    }

    private void handleUnmarkCommand(String command) {
        tasks.unmarkTaskAsDone(command);
        storage.saveTasksToFile(tasks);
    }

    private void handleTodoCommand(String command) {
        tasks.addTodoTask(command);
        storage.saveTasksToFile(tasks);
    }

    private void handleDeadlineCommand(String command) {
        tasks.addDeadlineTask(command);
        storage.saveTasksToFile(tasks);
    }

    private void handleEventCommand(String command) {
        tasks.addEventTask(command);
        storage.saveTasksToFile(tasks);
    }

    private void handleDeleteCommand(String command) {
        tasks.deleteTask(command);
        storage.saveTasksToFile(tasks);
    }

    private void handleFindCommand(String command) {
        tasks.findTask(command);
    }
}
