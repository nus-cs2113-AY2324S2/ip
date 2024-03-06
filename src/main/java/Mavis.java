/**
 * Represents a virtual assistant named Mavis that helps users manage their tasks.
 * Mavis can add, delete, mark, unmark, list, find, and perform other operations on tasks.
 */
public class Mavis {

    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "./data/mavis.txt";

    /**
     * Constructs a Mavis object with the specified file path for task data storage.
     *
     * @param filePath The file path for task data storage.
     */
    public Mavis(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Mavis application, allowing users to interact with the assistant and manage their tasks.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (MavisException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Mavis application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Mavis(FILE_PATH).run();
    }
}
