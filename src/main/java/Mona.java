import mona.input.Parser;
import mona.input.Ui;
import mona.output.ConsolePrint;
import mona.manager.TaskList;
import mona.util.Constants;
import mona.storage.Storage;

/**
 * The main class of the Mona application, responsible for initializing the application.
 */
public class Mona {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Mona. Initializes the user interface, storage,
     * and task list components of the application.
     */
    public Mona() {
        this.ui = new Ui();
        this.storage = new Storage(Constants.DATA_FILE_PATH);
        this.taskList = new TaskList((this.storage).loadData());
    }

    /**
     * Runs the Mona application, accepting user commands, parsing and
     * executing them until the "bye" command is received.
     */
    public void run() {
        String userCommand = ui.getUserInput();

        while (!userCommand.equals("bye")) {
            Parser inputParser = new Parser(userCommand);

            if (inputParser.isValidInput()) {
                taskList.executeCommand(inputParser.getCommandTypeAndParams());
                storage.saveToStorage(taskList.getTasks());
            }

            userCommand = ui.getUserInput();
        }
        ConsolePrint.exit();
    }

    /**
     * The main entry point of the Mona application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Mona().run();
    }
}
