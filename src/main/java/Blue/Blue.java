package Blue;

import java.util.Scanner;

public class Blue {
    private static final String WELCOME_MESSAGE = "Greetings! I'm Blue, a command line task assistant.";
    private static final String GOODBYE_MESSAGE = "Till we meet again.";

    private static void run() {
        Ui blueUi = new Ui();
        blueUi.talk(WELCOME_MESSAGE);
        new StorageHandler().restoreTasks();
        while (true) {
            Input userRequest = blueUi.getRequest();
            if (userRequest.isExit()) {
                break;
            }
            if (userRequest.isUndefined()) {
                blueUi.warn(userRequest.getErrorMessage());
                continue;
            }
            new TaskManager(userRequest).performRequest();
        }
        blueUi.talk(GOODBYE_MESSAGE);
    }

    public static void main(String[] args) {
        run();
    }
}
