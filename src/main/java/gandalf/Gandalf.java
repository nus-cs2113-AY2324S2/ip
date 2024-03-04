package gandalf;

public class Gandalf {

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
