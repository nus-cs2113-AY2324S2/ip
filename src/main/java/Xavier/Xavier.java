package Xavier;

import Exceptions.XavierException;

/**
 * Entry point of the Xavier application.
 * Initializes the application and starts the interaction with the user.
 */
public class Xavier {
    public static final String FILEPATH = "./data/toDoList.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up the required objects, loads up the data from the storage file
     *
     * @param filePath specifies the path to the file which stores the todo list
     *
     */
    public Xavier(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (XavierException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /** Runs the program until termination.  */
    public void run() {
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        while (!ui.isExit) {
            ui.readCommand(parser, tasks, ui, storage);
        }
    }


    public static void main(String[] args) {
        new Xavier(FILEPATH).run();
    }
}

