/**
 * The main class for the Sunny task management application.
 */
public class Sunny {
    static TaskList tasksList = new TaskList();
    public static final String FILE_PATH = "./data/sunny.txt";
    private static Ui ui = new Ui();
    private static Storage storage = new Storage(FILE_PATH);
    private static Parser parser = new Parser(storage, tasksList);

    /**
     * The main entry point of the Sunny application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        storage.loadTasksFromFile(tasksList);
        ui.showWelcome();
        parser.readCommand();
    }
}
