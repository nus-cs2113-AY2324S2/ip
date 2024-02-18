import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class UserInterface {
    Scanner myScanner = new Scanner(System.in);
    TaskManager taskManager = new TaskManager();

    public UserInterface() {
        FileProcessor.startUpCheck("./src/main/data.txt");
        try {
            FileProcessor.printFileContents("./src/main/data.txt");
        }
        catch (IOException e) {
            System.out.println("input output error");
        }
        String userInput = myScanner.nextLine();
        processUserCommand(userInput);
    }

    public void processUserCommand(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.equals("bye")) {
            System.out.println("Bye. I will be awaiting your next order! ☺");
        } else if (userInput.equals("list")) {
            taskManager.showListContents();
            userInput = myScanner.nextLine();
            processUserCommand(userInput);
        } else if (userInput.contains("mark")) {
            taskManager.changeTaskStatus(userInput);
            userInput = myScanner.nextLine();
            processUserCommand(userInput);
        } else if (userInput.contains("todo") || userInput.contains("event") || userInput.contains("deadline")){
            taskManager.addListContents(userInput);
            userInput = myScanner.nextLine();
            processUserCommand(userInput);
        }
        else {
            System.out.println("Please enter a valid command. Here have a cup of tea as you think of your next command. ☕");
            userInput = myScanner.nextLine();
            processUserCommand(userInput);
        }
    }
}
