package bob;

public class Parser {
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