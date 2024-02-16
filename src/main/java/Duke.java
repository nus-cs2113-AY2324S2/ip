public class Duke {
    public Duke() {
    }

    public static void main(String[] args) {
        Formatter.printWelcomeMsg();
        while (CommandExecutor.isRunning) {
            try {
                CommandExecutor.beginListening();
                CommandExecutor.processInput();
                CommandExecutor.executeCommand();
            } catch (ProcessInputException e) {
                Formatter.printErrorExecutionFail();
            }
        }
    }
}
