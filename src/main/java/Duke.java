public class Duke {
    public Duke() {
    }

    public static void main(String[] args) {
        Formatter.printWelcomeMsg();
        while (CommandExecutor.isRunning) {
            CommandExecutor.beginListening();
            CommandExecutor.processInput();
            CommandExecutor.executeCommand();
        }
    }
}
