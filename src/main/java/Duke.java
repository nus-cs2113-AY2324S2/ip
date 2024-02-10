public class Duke {
    public Duke() {
    }

    public static void main(String[] args) {
        Formatter.printWelcomeMsg();
        while (CommandExecutor.isRunning) {

            CommandExecutor.beginListening();
            CommandParser readUserCommand = new CommandParser(CommandExecutor.userInput);
            CommandExecutor.executeCommand(readUserCommand);
        }
    }
}
