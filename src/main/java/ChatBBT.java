import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import todolist.ToDoList;
import todolist.DataManager;

public class ChatBBT {

    private static final String FILE_PATH = "ChatBBTData.txt";

    /**
     * Print the exit message
     */
    public static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print the message divider
     */
    public static void messageDivider() {
        System.out.println("------------------------------------------");
    }

    /**
     * Print the greeting message
     */
    public static void greetingMessage() {
        System.out.println("Hello! I'm ChatBBT");
        System.out.println("What can I do for you?");
        messageDivider();
    }

    /**
     * Print the feature intro message
     */
    public static void featureIntroMessage() {
        System.out.println("[list] Enter Todo List feature");
        System.out.println("[bye] Quit");
        System.out.println("Please enter a command:");
        messageDivider();
    }

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create a new file
        DataManager dataManager = new DataManager(FILE_PATH);
        dataManager.createLocalDataFile();
        // create a new ToDoList
        ToDoList newToDoList = new ToDoList();
        dataManager.loadDataTodoList(newToDoList);

        greetingMessage();
        Scanner input = new Scanner(System.in);
        boolean isFinished = false;

        while(!isFinished) {
            featureIntroMessage();
            String inputText = input.nextLine();
            switch (inputText) {
            case "bye":
                exitMessage();
                isFinished = true;
                input.close();
                break;
            case "list":
                newToDoList.execute(input, dataManager);
                break;
            default:
                System.out.println("Invalid command");
            }
        }
    }
}
