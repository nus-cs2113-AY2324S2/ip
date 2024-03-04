import java.util.Arrays;
import java.util.Scanner;

public class Sunny {
    public static void main(String[] args) {
        String chatBotName = "Sunny";
        Task[] tasks = new Task[100];
        int counter = 0; //Counter to keep track of the number of tasks

        //Greets the user
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(" ");

        //Initialises scanner for user input
        Scanner scanner = new Scanner(System.in);

        //Echoes commands and performs actions based on user input
        while (true) {
            //Gets user input
            String command = scanner.nextLine();

            //Checks the command and perform the corresponding actions
            if (command.equalsIgnoreCase("bye")) {
                //Exit message
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");

                //Displays the list of tasks
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
                System.out.println(" ");
            } else if (command.startsWith("mark")) {
                //Mark a task as done
                int taskIndex = extractTaskIndex(command);
                if (taskIndex > 0 && taskIndex <= counter) {
                    tasks[taskIndex - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskIndex - 1]);
                } else {
                    System.out.println("Invalid task index. Please provide a valid task index." + System.lineSeparator());
                }
                System.out.println(" ");
            } else if (command.startsWith("unmark")) {
                //Unmark a task
                int taskIndex = extractTaskIndex(command);
                if (taskIndex > 0 && taskIndex <= counter) {
                    tasks[taskIndex - 1].unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskIndex - 1]);
                } else {
                    System.out.println("Invalid task index. Please provide a valid task index." + System.lineSeparator());
                }
                System.out.println(" ");
            }
            else {
                //Display the added task message
                System.out.println("Got it. I've added this task: ");

                //Add the command to the task array
                //tasks[counter] = new Task(command);

                if (command.startsWith("todo")) {
                    tasks[counter] = new Todo(command.substring(5));
                } else if (command.startsWith("deadline")) {
                    int dividerPosition = command.indexOf("/by ");
                    tasks[counter] = new Deadline(command.substring(0, dividerPosition - 1), command.substring(dividerPosition + 3)); //needa add the by thing
                } else if (command.startsWith("event")) {
                    int from = command.indexOf("/from ");
                    int to = command.indexOf("/to ");
                    tasks[counter] = new Event(command.substring(0, from - 1), command.substring(from + 5, to - 1), command.substring(to + 3)); //needa add the from and to thing
                }
                System.out.println(tasks[counter]);
                counter++;
                if (counter == 1) {
                    System.out.println("Now you have " + counter + " task in the list." + System.lineSeparator());
                } else {
                    System.out.println("Now you have " + counter + " tasks in the list." + System.lineSeparator());
                }
            }
        }
    }

    // Helper method to extract the task index from commands like "mark 2"
    private static int extractTaskIndex(String command) {
        try {
            return Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }
}

