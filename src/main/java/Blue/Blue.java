package Blue;

import java.util.Scanner;

/**
 * The Blue class constitutes the main logic of our chatbot application.
 * It takes a string from the standard input, parses it into a well-formed request and handles appropriately.
 *
 * @author nkotaa
 */
public class Blue {
    private Ui blueUi;
    private TaskManager blueTaskManager;

    /**
     * Class constructor for Blue.
     */
    public Blue() {
        blueUi = new Ui();
        blueTaskManager = new TaskManager(blueUi);
    }

    private void run() {
        blueUi.greet();
        Input userRequest = blueUi.getRequest(); 
        while (userRequest.isNotExit()) {
            while (userRequest.isUndefined()) {
                userRequest = blueUi.getRequest();
            }
            blueTaskManager.performRequest(userRequest);
            userRequest = blueUi.getRequest();
        }
        blueUi.farewell();
    }

    /**
     * The main method of our program
     */
    public static void main(String[] args) {
        new Blue().run();
    }
}
