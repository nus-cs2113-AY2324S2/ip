import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Display initial greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        //Create Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] tasks = new Task[100]; // Array to store tasks
        int taskCount = 0;

        //Continue reading user input, detecting any keywords and executing the respective commands
        while (true) {
            userInput = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (userInput.equalsIgnoreCase("bye")) {
                //Exit bot
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                //Lists out all the tasks added, else print out no tasks added
                if (taskCount == 0) {
                    System.out.println("No tasks added yet.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i]);
                    }
                }

            } else if (userInput.startsWith("mark")) {
                //Feature to mark tasks as done
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex].getStatusIcon() + " " + tasks[taskIndex].getDescription());

            } else if (userInput.startsWith("unmark")) {
                //Feature to unmark tasks as not done
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex].getStatusIcon() + " " + tasks[taskIndex].getDescription());

            } else if (userInput.startsWith("todo")) {
                //Feature to track tasks without any date/time attached to it
                String description = userInput.substring(5).trim();
                tasks[taskCount] = new ToDo(description);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");

            } else if (userInput.startsWith("deadline")) {
                //Feature to track tasks that need to be done before a specific date/time
                String[] parts = userInput.substring(9).split("/by");
                String description = parts[0].trim();
                String by = parts[1].trim();
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                
            } else if (userInput.startsWith("event")) {
                //Feature to track tasks that start at a specific date/time and ends at a specific date/time
                String[] parts = userInput.substring(6).split("/from|/to");
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");

            } else {
                //Handles the case if invalid input
                System.out.println("Invalid command.");
            }
            System.out.println("____________________________________________________________");
        }
        // Close the Scanner
        scanner.close();
    }
}