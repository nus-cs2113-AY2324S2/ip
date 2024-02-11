import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

//test merge functionality

public class John {

    private static List<Task> taskList = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void parseInput(String input) {

        int taskID;

        switch(input.toLowerCase()) {
            
        case "list":
            for (int i = 1; i <= taskList.size(); i += 1) {
                System.out.print(i + ".");
                System.out.println(taskList.get(i - 1));
            }
            break;

        case "bye":
            break;

        case "mark":
            taskID = in.nextInt();

            if (taskID > taskList.size() || taskID < 1) {
                System.out.println("Out of range");
                break;
            }

            taskList.get(taskID - 1).markCompleted();
            System.out.println("Marking as done:");
            System.out.println(taskList.get(taskID - 1));
            break;

        case "unmark":
            taskID = in.nextInt();

            if (taskID > taskList.size()) {
                System.out.println("Out of range");
                break;
            }

            taskList.get(taskID - 1).markUncompleted();
            System.out.println("Marking as undone:");
            System.out.println(taskList.get(taskID - 1));
            break;

        case "todo": 
            input = in.nextLine();
            taskList.add(new ToDo(input.trim()));
            System.out.println("Added ToDo: " + input);
            break;

        case "deadline":
            input = in.nextLine();
            taskList.add(new Deadline(input));
            System.out.println("Added Deadline: " + taskList.get(taskList.size() - 1).getName());
            break;
        
        case "event":
            input = in.nextLine();
            taskList.add(new Event(input));
            System.out.println("Added Event: " + taskList.get(taskList.size() - 1).getName());
            break;

        default:
            Task task = new Task(input + in.nextLine());
            taskList.add(task);
            System.out.println("Added: " + taskList.get(taskList.size() - 1).getName());
            break;
        }
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm John Chadbot.\n" + "What can I do for you?\n");

        String userInput = in.next();

        while (!userInput.equalsIgnoreCase("bye")) {
            parseInput(userInput);
            userInput = in.next();
            System.out.println("");
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
