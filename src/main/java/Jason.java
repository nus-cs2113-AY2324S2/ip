import java.util.Scanner;

/**
 * Represents the main class for the application Duke.
 */
public class Jason {
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

    private static void showTaskNumber() {
        if (num == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println("Now you have " + num + " tasks in the list");
        }

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
        showTaskNumber();
    }

    private static void deadlineTasks(String task) {
        String[] parts = task.split("/by", 2);
        String taskDescription = parts[0].substring(9);
        String taskDeadlineBy = parts[1];
        list[num] = new Deadline(taskDescription, taskDeadlineBy);
        System.out.println("Got it. I've added this task:");
        System.out.println(list[num].toString());
        num++;
        showTaskNumber();
    }


    private static void eventTasks(String task) {
        String[] parts = task.split("/from", 2);
        String taskDescription = parts[0].substring(6);
        String[] eventTimeline = parts[1].split("/to", 2);
        String eventStartFrom = eventTimeline[0];
        String eventTill = eventTimeline[1];
        list[num] = new Events(taskDescription, eventStartFrom, eventTill);
        System.out.println("Got it. I've added this task:");
        System.out.println(list[num].toString());
        num++;
        showTaskNumber();
    }





    private static void addTasks(String input) {
        if (input.startsWith("todo")) {
            todoTasks(input);
        } else if (input.startsWith("deadline")) {
            deadlineTasks(input);
        } else if (input.startsWith("event")) {
            eventTasks(input);
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
