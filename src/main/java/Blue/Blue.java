package Blue;

import java.util.Scanner;

/**
 * The Blue class constitutes the main logic of our chatbot application.
 * It takes a string from the standard input, parses it into a well-formed request and handles appropriately.
 *
 * @author nkotaa
 */
public class Blue {
    private static final String WELCOME_MESSAGE = "Greetings! I'm Blue, a command line task assistant.";
    private static final String GOODBYE_MESSAGE = "Till we meet again.";

    private static void run() {
        new StorageHandler().restoreTasks();
        Ui blueUi = new Ui();
        blueUi.talk(WELCOME_MESSAGE);
        for (Input userRequest = blueUi.getRequest(); !userRequest.isExit();
                userRequest = blueUi.getRequest()) {
            if (userRequest.isUndefined()) {
                blueUi.warn(userRequest.getErrorMessage());
                continue;
            }
            new TaskManager(userRequest).performRequest();
        }
        blueUi.talk(GOODBYE_MESSAGE);
    }

    /**
     * The main method of our program
     */
    public static void main(String[] args) {
        run();
    }
}
