import java.io.IOException;
import java.util.Scanner;

/**
 * The UI class manages the user interface and interaction with the task manager.
 * It processes user commands and displays appropriate messages or performs actions accordingly.
 * The UI prompts the user for input and continuously processes commands until the user decides to exit.
 */
public class Ui {
    /**
     * The file path for storing task data.
     */
    public static final String DATA_TXT_FILE_PATH = "./data.txt";

    /**
     * Scanner object for reading user input.
     */
    Scanner myScanner = new Scanner(System.in);

    /**
     * TaskManager object for managing tasks.
     */
    TaskManager taskManager = new TaskManager();

    /**
     * Flag indicating whether to continue receiving user inputs.
     */
    private boolean continueReceivingUserInputs = true;

    /**
     * Constructs a new Ui object.
     * Performs startup checks and begins processing user commands.
     */
    public Ui() {
        try {
            Storage.startUpCheck(DATA_TXT_FILE_PATH);
        }
        catch (IOException e) {
            System.out.println("input output error");
        }
        String userInput = myScanner.nextLine();
        processUserCommand(userInput);
    }


    /**
     * Processes user commands based on the provided input.
     * The method gets the next user command until the user decides to exit.
     *
     * @param userInput The user input command to be processed.
     */
    public void processUserCommand(String userInput) {
        while (continueReceivingUserInputs) {
            userInput = userInput.toLowerCase();
            if (userInput.equals("bye")) {
                System.out.println();
                System.out.println("Bye. I will be awaiting your next order! =)");
                continueReceivingUserInputs = false;
            } else if (userInput.equals("list")) {
                System.out.println();
                taskManager.showListContents();
                System.out.println();
                userInput = myScanner.nextLine();
            } else if (userInput.contains("mark")) {
                System.out.println();
                taskManager.changeTaskStatus(userInput);
                System.out.println();
                userInput = myScanner.nextLine();
            } else if (userInput.contains("todo") || userInput.contains("event") || userInput.contains("deadline")){
                System.out.println();
                taskManager.addListContents(userInput);
                System.out.println();
                userInput = myScanner.nextLine();
            } else if (userInput.contains("delete")) {
                System.out.println();
                taskManager.deleteTask(userInput);
                System.out.println();
                userInput = myScanner.nextLine();
            } else if (userInput.contains("find")) {
                System.out.println();
                taskManager.findTask(userInput);
                System.out.println();
                userInput = myScanner.nextLine();
            } else {
                System.out.println();
                System.out.println("Please enter a valid command. Here have a cup of tea as you think of your next command. =/");
                System.out.println();
                userInput = myScanner.nextLine();
                processUserCommand(userInput);
            }
        }
    }
}
