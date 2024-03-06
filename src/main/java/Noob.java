import ui.Ui;

/**
 * Main class for Noob chabot
 */
public class Noob {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcomeMessage();
        ui.startConversation();
        ui.printGoodbyeMessage();
    }
}
