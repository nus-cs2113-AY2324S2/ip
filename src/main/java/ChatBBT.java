import java.util.Scanner;
import ToDoListFeature.ToDoItem;
import ToDoListFeature.ToDoList;

public class ChatBBT {

    public static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void greetingMessage() {
        System.out.println("Hello! I'm ChatBBT");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        greetingMessage();
        Scanner input = new Scanner(System.in);
        boolean isFinished = false;
        ToDoList newToDoList = new ToDoList();

        while(!isFinished) {
            String inputText = input.nextLine();
            switch (inputText) {
            case "bye":
                exitMessage();
                isFinished = true;
                break;
            case "list":
                System.out.println(newToDoList.toString());
                System.out.println("------------------------------------------");
                break;
            default:
                newToDoList.addItem(new ToDoItem(inputText));
                System.out.println("added: " + inputText);
                System.out.println("------------------------------------------");
            }

        }

    }
}
