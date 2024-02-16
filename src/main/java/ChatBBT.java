import java.io.File;
import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;
import todolist.ToDoList;

public class ChatBBT {

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

    public static void createLocalDataFile() {
        String filePath = "ChatBBTData.txt";
        try {
            // Create a File object
            File file = new File(filePath);
            // Check if the file already exists
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("App data stored in " + file.getAbsolutePath());
            }
            System.out.println("Please do not delete the file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void main(String[] args) {
        createLocalDataFile();
        greetingMessage();
        Scanner input = new Scanner(System.in);
        boolean isFinished = false;
        ToDoList newToDoList = new ToDoList();
        newToDoList.loadData();

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
                newToDoList.execute(input);
                break;
            default:
                System.out.println("Invalid command");
            }
        }
    }
}
