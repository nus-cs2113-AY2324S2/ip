package gandalf;

public class Gandalf {
    /**
     * Starts the Gandalf application and keeps reading user input
     * until user shuts down the system by typing "bye".
     */
    public void run() {
        Ui.printWelcomeMessage();
        Ui.initializeGandalf();
        while (!Ui.isExit) {
            Ui.readUserInput();
        }
        Ui.printEndMessage();
    }

    public static void main(String[] args) {
        new Gandalf().run();
    }
}
