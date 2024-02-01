import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class John {

    private static List<Task> taskList = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void parseInput(String input) {

        int taskID;

        switch(input.toLowerCase()) {
        case "list":
            for (int i = 1; i <= taskList.size(); i += 1) {
                System.out.print(i + ".");
                taskList.get(i - 1).printFull();
            }
            break;

        case "bye":
            break;

        case "mark":
            taskID = in.nextInt();

            if (taskID > taskList.size()) {
                System.out.println("Out of range");
                break;
            }

            taskList.get(taskID - 1).markCompleted();
            System.out.println("Marking as done:");
            taskList.get(taskID - 1).printFull();
            break;

        case "unmark":
            taskID = in.nextInt();

            if (taskID > taskList.size()) {
                System.out.println("Out of range");
                break;
            }

            taskList.get(taskID - 1).markUncompleted();
            System.out.println("Marking as undone:");
            taskList.get(taskID - 1).printFull();
            break;

        default:
            Task task = new Task(input);
            taskList.add(task);
            System.out.println("Added: " + input);
            break;
        }
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm John Chadbot.\n" + "What can I do for you?\n");

        String userInput = in.next();

        while (!userInput.equalsIgnoreCase("bye")) {
            parseInput(userInput);
            userInput = in.next();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
