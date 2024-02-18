import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class UserInterface {
    public static final String DATA_TXT_FILE_PATH = "./src/main/data.txt";
    Scanner myScanner = new Scanner(System.in);
    TaskManager taskManager = new TaskManager();

    private boolean continueReceivingUserInputs = true;

    public UserInterface() {
        try {
            FileProcessor.startUpCheck(DATA_TXT_FILE_PATH);
        }
        catch (IOException e) {
            System.out.println("input output error");
        }
        String userInput = myScanner.nextLine();
        processUserCommand(userInput);
    }

    public void processUserCommand(String userInput) {
        while (continueReceivingUserInputs) {
            userInput = userInput.toLowerCase();
            if (userInput.equals("bye")) {
                System.out.println("Bye. I will be awaiting your next order! ☺");
                continueReceivingUserInputs = false;
            } else if (userInput.equals("list")) {
                taskManager.showListContents();
                userInput = myScanner.nextLine();
            } else if (userInput.contains("mark")) {
                taskManager.changeTaskStatus(userInput);
                userInput = myScanner.nextLine();
            } else if (userInput.contains("todo") || userInput.contains("event") || userInput.contains("deadline")){
                taskManager.addListContents(userInput);
                userInput = myScanner.nextLine();
            } else if (userInput.contains("delete")) {
                taskManager.deleteTask(userInput);
                userInput = myScanner.nextLine();
            } else {
                System.out.println("Please enter a valid command. Here have a cup of tea as you think of your next command. ☕");
                userInput = myScanner.nextLine();
                processUserCommand(userInput);
            }
        }
    }
}
