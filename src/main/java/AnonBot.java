public class AnonBot {
    private static void runMainLoop() {
        Status commandStatus = Status.STATUS_OK;
        while (commandStatus != Status.STATUS_EXIT) {
            String userInput = Ui.getUserInput();
            commandStatus = CommandManager.processCommand(userInput);
        }
    }

    public static void main(String[] args) {
        Ui.printGreetings();
        runMainLoop();
    }
}
