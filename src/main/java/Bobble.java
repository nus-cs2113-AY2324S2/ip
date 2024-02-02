import java.util.Arrays;
import java.util.Scanner;

public class Bobble {
    public static void main(String[] args) {
        start();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        Task[] tasklist = new Task[100];
        int taskCount = 0;

        while (!userInput.equals("bye")) {
            switch (userInput) {
            case "list":
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ".[" + tasklist[i].getStatusIcon() + "] " + tasklist[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
                break;
            default:
                if (userInput.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                    tasklist[taskNumber].setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:\n" +
                            "  [ ] " + tasklist[taskNumber].getDescription() +
                            "\n____________________________________________________________\n");
                } else if (userInput.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                    tasklist[taskNumber].setDone(true);
                    System.out.println("Nice! I've marked this task as done:\n" +
                            "  [X] " + tasklist[taskNumber].getDescription() +
                            "\n____________________________________________________________\n");
                } else {
                    // add task to list
                    tasklist[taskCount++] = new Task(userInput);
                    System.out.println("____________________________________________________________\n" +
                            "added: " + userInput +
                            "\n____________________________________________________________\n");
                }
            }
            userInput = input.nextLine();
        }
        goodbye();
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
