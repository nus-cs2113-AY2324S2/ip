import HikoUi.Ui;
import Events.TaskList;
import Storage.Store;
import Events.Echo;

public class Hiko {

    private Ui ui;
    private Store store;
    private TaskList tasks;

    /**
     * Constructs a Hiko Object
     * Initializes the user interface and task list.
     *
     * @param filePath The filepath of the data.
     */

    public Hiko(String filePath) {
        ui = new Ui(); // Initializes the UI component
        store = new Store(filePath); // Initializes the Store with the file path for task persistence
        tasks = new TaskList(); // Initializes the TaskList
        store.setTaskList(tasks); // Links the TaskList with the Store for loading and saving
    }

    /**
     * Initilize the bot with UI
     * Start the Echo interaction loop
     *
     *
     */

    public void start() {
        Echo echo = new Echo(ui, store); // Initializes the Echo component with UI
        echo.HikoStart(tasks); // Starts the Echo interaction loop
    }

    /**
     * The mian method to start the Hiko application
     *
     * @param args The command line takes in arguments
     */

    public static void main(String[] args) {
        String filePath = "./data/duke.txt"; // Define the file path for storing tasks
        Hiko hiko = new Hiko(filePath); // Create an instance of Hiko with the specified file path
        hiko.start(); // Start the chatbot
    }
}
