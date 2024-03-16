import java.util.ArrayList;

/**
 * It initializes the user interface, storage, and parser components, and orchestrates
 * the flow of the application.
 */
public class Zap {
    //    private static List<Task> tasks;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Constructs a Zap instance with the specified file path for task storage.
     * It initializes the UI, storage, and parser components, and loads tasks from storage.
     *
     * @param filePath The path to the file used for task storage.
     */

    public Zap(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ArrayList<Task> tasks = storage.load(FILE_PATH);
        TaskList taskList = new TaskList();
        taskList.load(tasks);

        this.parser = new Parser();
    }

    /**
     * Starts the application, displaying instructions, parsing user commands,
     * and saving tasks upon completion.
     */

    public void run() {
        ui.printInstructions();
        parser.parseCommands();
        ui.printFarewell();
    }

    /**
     * Point of entry for the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Zap("tasks.txt").run();
    }
}