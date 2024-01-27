import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class John {

    private static List<Task> taskList = new ArrayList<Task>();

    public static void parseInput(String input) {

        switch(input.toLowerCase()) {
        case "list":
            for (int i = 1; i <= taskList.size(); i += 1) {
                System.out.println(i + ": " + taskList.get(i - 1).getName());
            }
            break;

        case "bye":
            break;

        default:
            Task task = new Task(input);
            taskList.add(task);
            System.out.println("Added: " + input);
            break;
        }
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm John Chatbot.\n" + "What can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {

            parseInput(userInput);
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
