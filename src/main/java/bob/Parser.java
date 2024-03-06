package bob;
/**
 * Parser class to handle user input.
 */
public class Parser {

    /**
     * Processes the user input and calls the appropriate methods.
     * @param command The command to be processed.
     * @param line The rest of the user input.
     * @param tasks The list of tasks.
     * @param storage The storage object.
     * @param ui The UI object.
     * @return True if the command is "bye", false otherwise.
     * @throws BobException If the command is not recognised.
     */
    static boolean processCommand(String command, String line, TaskList tasks, Storage storage, Ui ui) throws BobException {

        switch (command) {
        case "todo":
            tasks.addTodo(line);
            storage.saveList(tasks.list);
            break;
        case "deadline":
            tasks.addDeadline(line);
            storage.saveList(tasks.list);
            ui.displaySaveMessage(storage.FILENAME);
            break;
        case "event":
            tasks.addEvent(line);
            storage.saveList(tasks.list);
            ui.displaySaveMessage(storage.FILENAME);
            break;
        case "mark":
            tasks.markTask(line);
            storage.saveList(tasks.list);
            ui.displaySaveMessage(storage.FILENAME);
            break;
        case "unmark":
            tasks.unmarkTask(line);
            storage.saveList(tasks.list);
            ui.displaySaveMessage(storage.FILENAME);
            break;
        case "list":
            ui.displayList(tasks.list);
            break;
        case "delete":
            tasks.deleteTask(line);
            storage.saveList(tasks.list);
            ui.displaySaveMessage(storage.FILENAME);
            break;
        case "save":
            storage.saveList(tasks.list);
            ui.displaySaveMessage(storage.FILENAME);
            break;
        case "find":
            tasks.findTask(line);
            break;
        case "bye":
            storage.saveList(tasks.list);
            ui.displaySaveMessage(storage.FILENAME);
            ui.displayExitMessage();
            return true;
        default:
            throw new BobException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }
}