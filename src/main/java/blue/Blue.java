package blue;

import blue.exception.BlueException;
import blue.storage.StorageHandler;
import blue.task.TaskManager;
import blue.ui.Ui;

/**
 * The Blue class constitutes the main logic of our chatbot application.
 * Broadly, it takes some request from standard input and handles if well-formed, warning otherwise.
 *
 * @author nkotaa
 */
public class Blue {
    private Ui blueUi;
    private TaskManager blueTaskManager;
    private StorageHandler blueStorageHandler;

    /**
     * Constructor for Blue, initializing the subcomponents it needs to run.
     */
    public Blue() {
        blueUi = new Ui();
        blueTaskManager = new TaskManager(blueUi);
        blueStorageHandler = new StorageHandler(blueTaskManager);
    }

    private void run() {
        blueUi.greet();
        blueStorageHandler.restoreState();
        while (blueUi.isUserActive()) {
            try {
                blueUi.nextRequest();
                blueTaskManager.performRequest();
                blueStorageHandler.saveState();
            } catch (BlueException e) {
                blueUi.warn(e.getMessage());
            }
        }
        blueUi.farewell();
    }

    public static void main(String[] args) {
        new Blue().run();
    }
}
