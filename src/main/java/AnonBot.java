public class AnonBot {
    private static void runMainLoop() {
        Status status = Status.STATUS_OK;
        while (status != Status.STATUS_EXIT) {
            String userInput = Ui.getUserInput();
            status = CommandManager.processCommand(userInput);
        }
    }

    public static void main(String[] args) {
        Ui.printGreetings();
        runMainLoop();
        Ui.printGoodbye();
    }
}
