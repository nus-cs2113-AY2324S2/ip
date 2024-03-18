import drosstasks.DrossList;
import utilityclasses.*;

/**
 * The main class for the Dross application, which manages tasks through a console-based user interface.
 * It initializes the application, processes user input, and terminates the application upon request.
 */
public class Dross {
    private static DrossList drossTaskList = new DrossList();
    private FileIO io;
    private Ui ui;
    private Parser parse;

    /**
     * Initializes the application, loads tasks from the file, and displays the welcome message.
     */
    private void start(){
        this.ui = new Ui();
        this.io = new FileIO();
        io.loadTasksFromFile(drossTaskList);
        ui.printWelcomeMessage();
    }

    /**
     * Prints the goodbye message and exits the application.
     */
    private void exit(){
        ui.printGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Enters the main loop of the application, parsing and executing user input until the exit command is received.
     */
    private void initiateReceiving(){
        parse = new Parser();
        parse.readParseExecuteUserInput(drossTaskList);
    }

    /**
     * Runs the Dross application, orchestrating the start, main loop, and exit processes.
     */
    public void run(){
        start();
        initiateReceiving();
        exit();
    }

    /**
     * The entry point of the application. Creates a new Dross instance and calls its run method.
     */
    public static void main(String[] args) {
        new Dross().run();
    }
}

