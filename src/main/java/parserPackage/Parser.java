package parserPackage;

import edithExceptionPackage.ChatBotExceptions;
import storagePackage.Storage;
import taskListPackage.TaskList;
import ui.Ui;

/**
 * Parses user commands and executes corresponding actions.
 */
public class Parser {

    /**
     * Processes the user command.
     *
     * @param command The user command to process.
     * @param tasks   The TaskList object containing tasks.
     * @param storage The Storage object for saving tasks.
     * @param ui      The Ui object for displaying messages.
     */
    public void processCommand(String command, TaskList tasks, Storage storage, Ui ui) {
        try {
            command = command.trim();

            String[] parts = command.split(" ", 2);
            String mainCommand = parts[0].toLowerCase();
            switch (mainCommand) {
            case "list":
                ui.printTaskList(tasks.getList());
                break;
            case "mark":
                if (parts.length > 1) {
                    tasks.markTaskAsDone(parts[1]);
                    storage.saveTasksToFile(tasks);
                }
                break;
            case "unmark":
                if (parts.length > 1) {
                    tasks.unmarkTaskAsDone(parts[1]);
                    storage.saveTasksToFile(tasks);
                }
                break;
            case "todo":
                if (parts.length > 1) {
                    tasks.addTask(parts[1], "todo");
                    storage.saveTasksToFile(tasks);
                }
                break;
            case "event":
                if (parts.length > 1) {
                    tasks.addTask(parts[1], "event");
                    storage.saveTasksToFile(tasks);
                }
                break;
            case "deadline":
                if (parts.length > 1) {
                    tasks.addTask(parts[1], "deadline");
                    storage.saveTasksToFile(tasks);
                }
                break;
            case "delete":
                if (parts.length > 1) {
                    tasks.deleteTask(parts[1]);
                    storage.saveTasksToFile(tasks);
                }
                break;
            case "find":
                if (parts.length > 1) {
                    String keyword = parts[1];
                    tasks.findTasksByKeyword(keyword);
                } else {
                    ui.printFormattedMessage("Please specify a keyword to search for.");
                }
                break;
            default:
                ui.printFormattedMessage("Unknown command. " +
                        "Please enter 'todo', 'event', 'deadline', " +
                        "'list', 'mark', 'unmark', 'delete', or 'bye'.");
            }
        } catch (ChatBotExceptions e) {
            ui.printFormattedMessage(e.getMessage());
        }
    }
}
