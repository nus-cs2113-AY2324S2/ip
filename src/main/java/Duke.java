public class Duke {
    public Duke() {
    }

    public static void main(String[] args) {
        String botName = "Hirofumi";
        System.out.println("************************************************************");
        System.out.println(" Hello! I'm " + botName);
        System.out.println(" What can I do for you?");
        System.out.println("************************************************************");


        while (CommandExecutor.isRunning) {

            CommandExecutor.beginListening();
            CommandParser readUserCommand = new CommandParser(CommandExecutor.userInput);
            CommandExecutor.executeCommand(readUserCommand);
        }
    }
}
