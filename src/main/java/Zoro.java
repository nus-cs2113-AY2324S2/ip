import Helper.CommandHandler;
import Helper.TaskManager;
import Helper.UserInterface;

import java.util.Scanner;

public class Zoro {

    public static final String BYE = "bye";

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        UserInterface userInterface = new UserInterface();
        userInterface.greetUser();

        CommandHandler commandHandler = new CommandHandler(taskManager, userInterface);
        boolean isRunning = true;
        while (isRunning) {
            String input = UserInterface.getUserInput(in);
            commandHandler.handleCommand(input);
            if (input.equalsIgnoreCase(BYE)) {
                isRunning = false;
            }
        }
        in.close();
    }
}
