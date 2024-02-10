import java.util.Scanner;

public class Duke {
    public Duke() {
    }
    static boolean isRunning = true;
    static final int MAX_NUMBERED_LIST_LENGTH = 100;
    static Task[] tasks = new Task[MAX_NUMBERED_LIST_LENGTH];
    static int listCount = 0;
    public static void main(String[] args) {
        String botName = "Hirofumi";
        System.out.println("************************************************************");
        System.out.println(" Hello! I'm " + botName);
        System.out.println(" What can I do for you?");
        System.out.println("************************************************************");

        Scanner in = new Scanner(System.in);

        while (isRunning) {
            String userInput = in.nextLine();
            CommandParser readUserCommand = new CommandParser(userInput);
            CommandExecutor.executeCommand(readUserCommand);
        }
    }
}
