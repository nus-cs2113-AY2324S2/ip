import java.util.Scanner;
public class UserInterface {
    Scanner myScanner = new Scanner(System.in);
    TaskManager taskManager = new TaskManager();

    public UserInterface() {
        String userInput = myScanner.nextLine();
        processUserCommand(userInput);
    }

    public void processUserCommand(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (userInput.equals("list")) {
            taskManager.showListContents();
            userInput = myScanner.nextLine();
            processUserCommand(userInput);
        } else if (userInput.contains("mark")) {
            taskManager.changeTaskStatus(userInput);
            userInput = myScanner.nextLine();
            processUserCommand(userInput);
        } else if (userInput.contains("todo") || userInput.contains("event") || userInput.contains("deadline")){
            System.out.println("Got it. I've added this task:");
            taskManager.addListContents(userInput);
            userInput = myScanner.nextLine();
            processUserCommand(userInput);
        }
        else {
            System.out.println("Please enter a valid command.");
            userInput = myScanner.nextLine();
            processUserCommand(userInput);
        }
    }
}
