import java.util.Scanner;

/**
 * Represents the main class for the application Duke.
 */
public class Duke {
    private static final int MAX_SIZE = 100;
    private static Task[] list = new Task[MAX_SIZE];
    private static int num = 0;


    private static void markTask(String[] input) {
        int taskNumber = Integer.parseInt(input[1]) - 1;
                list[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[taskNumber]);
    }


    private static void unmarkTask(String[] input) {
        int taskNumber = Integer.parseInt(input[1]) - 1;
                list[taskNumber].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[taskNumber]);

    }


    private static void showList() {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < num; i++) {
            System.out.println(i + 1 + ":" + list[i]);
        }

    }

    private static void todoTasks(String task) {

        String taskDescription = task.substring(5);   //input this into the function
                    list[num] = new Todo(taskDescription);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[num].toString());
                    num++;
    }

    private static void DeadlineTasks(String task) {
        
    }


    private static void addTasks(String input) {
        if (input.startsWith("todo")) {
            todoTasks(input);
        }

    }



    /**
     * Main entry point of the Chatbot.
     * Initializes the application and starts the interaction with the user.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Initialize greeting logo and print welcome message
        String logo =
                "   J     A     SSSS    OOO   N   N \n"
                        + "   J    A A    S      O   O  NN  N \n"
                        + "   J   A A A    SSS   O   O  N N N \n"
                        + "J  J  A     A      S  O   O  N  NN \n"
                        + " JJJ A       A  SSSS   OOO   N   N \n" +
                        "                 \n";
        System.out.println(logo + " Eyy wassup I'm Jason\r\n"
                + " What can I do for you?\r\n"
        );

        Scanner scanner = new Scanner(System.in);
        String input;
        

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                // User wants to exit
                System.out.println("Bye. See ya laterr!\r\n"
                        + "____________________________________________________________\r\n"
                        + "\n" + logo);
                break;
            }

            // Split the input and check for commands
            String[] parts = input.split(" ");
            if (parts[0].equalsIgnoreCase("mark")) {
                // Mark task as done
                markTask(parts);
            } else if (parts[0].equalsIgnoreCase("unmark")) {
                // Mark task as not done
                unmarkTask(parts);
            } else if (parts[0].equalsIgnoreCase("list")) {
                // List all tasks
                showList();
            } else {
                // Add new task
                addTasks(input);
               
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
