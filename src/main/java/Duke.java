import Event.TaskList;
import RuntimeSupport.DukeException;
import RuntimeSupport.Parser;
import RuntimeSupport.Storage;
import RuntimeSupport.Ui;

/**
 * The <code>Duke</code> class serves as the main entry point for the Duke application,
 * a personal assistant bot designed to help users manage their tasks. Duke, also known
 * as 500W, offers a simple and intuitive way to add, delete, and query tasks,which can
 * include todos, deadlines, and events.
 * <p>
 * The application remembers tasks across sessions by saving them to a file specified by
 * the user.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Duke object with the specified file path for data storage.
     * If a file cannot be loaded, a new file will be created in the designated
     * file path.
     *
     * @param filePath The file path where tasks are saved and loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the application by showing a welcome message and runs
     * the command processing loop.
     * Loop will stop when the boolean variable isExit becomes true,
     * it occurs when the user inputs "bye" and quits the bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            isExit = Parser.execution(command, tasks, ui, storage);
        }
    }

    /**
     * The main entry point of the application.
     * Initiates the application with a specified file path for data
     * storage.
     *
     * @param args Command line arguments can be used as the file
     * path for data storage. Currently, it defaults to "./duke.txt"
     * in the main directory.
     */
    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}