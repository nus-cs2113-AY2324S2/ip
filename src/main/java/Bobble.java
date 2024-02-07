import java.util.Arrays;
import java.util.Scanner;

public class Bobble {
    public static void main(String[] args) {
        start();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        Task[] tasklist = new Task[100];
        int taskCount = 0;
        String command;
        String description;

        while (!userInput.equals("bye")) {
            String[] UserInputs = splitUserInput(userInput);
            command = UserInputs[0];
            switch (command) {
            case "list":
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasklist[i].toString());
                }
                System.out.println("____________________________________________________________\n");
                break;
            case "todo":
                tasklist[taskCount] = new ToDo(UserInputs[1]);
                addTaskResponse(tasklist, taskCount);
                taskCount++;
                break;
            case "deadline":
                String[] parts = UserInputs[1].split("/by");
                tasklist[taskCount] = new Deadline(parts[0], parts[1]);
                addTaskResponse(tasklist,taskCount);
                taskCount++;
                break;
            case "event":
                parts = UserInputs[1].split("/from");
                String[] duration = parts[1].split("/to");
                tasklist[taskCount] = new Event(parts[0], duration[0], duration[1]);
                addTaskResponse(tasklist, taskCount);
                taskCount++;
                break;
            case "mark":
                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                tasklist[taskNumber].setDone(true);
                System.out.println("Nice! I've marked this task as done:\n" +
                        tasklist[taskNumber].toString() +
                        "\n____________________________________________________________\n");
                break;
            case "unmark":
                taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                tasklist[taskNumber].setDone(false);
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        tasklist[taskNumber].toString() +
                "\n____________________________________________________________\n");
                break;
            default:
            }
            userInput = input.nextLine();
        }
        goodbye();
    }

    public static String[] splitUserInput(String input) {
        String[] userInput = input.split(" ", 2);
        return userInput;
    }

    public static void addTaskResponse(Task[] tasklist, int taskCount) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task: \n  " +
                tasklist[taskCount].toString() +
                "\nNow you have " + (taskCount + 1) + " task(s) in the list." +
                "\n____________________________________________________________\n");
    }

    //Greets user
    public static void start() {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Bobble.\n" +
                "What can I do for you?" +
                "\n____________________________________________________________\n");
    }

    //Exits
    public static void goodbye() {
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

}
