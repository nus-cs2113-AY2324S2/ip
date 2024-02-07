import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Printing the logo and greeting message
        String logo = "  _________   _____      _____   \n" +
                " /   _____/  /  _  \\    /     \\  \n" +
                " \\_____  \\  /  /_\\  \\  /  \\ /  \\ \n" +
                " /        \\/    |    \\/    Y    \\\n" +
                "/_______  /\\____|__  /\\____|__  /\n" +
                "        \\/         \\/         \\/ ";
        System.out.println("Hello! I'm SAM\n" + logo + "\n" + "What can I do for you?\n");

        // Initializing scanner to read user input
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        // Array to store tasks
        Task[] records = new Task[100];
        int numItems = 0; // Number of tasks
        int listIndex; // Index for tasks

        // Main loop
        while (!line.equals("bye")) {
            String[] words = line.split(" ");
            System.out.println("____________________________________________________________");

            // Checking user input
            switch (words[0]) {
            case "list":
                // Listing tasks
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < numItems; j++) {
                    System.out.println((j + 1) + "." + records[j]);
                }
                System.out.println("Now you have " + numItems + (numItems == 1 ? " task " : " tasks ") + "in the list.");
                break;
            case "mark":
                // Marking a task as done
                listIndex = Integer.parseInt(words[1]) - 1;
                records[listIndex].isDone = true;
                System.out.println("Nice! I've marked this task as done:\n" + records[listIndex]);
                break;
            case "unmark":
                // Marking a task as not done
                listIndex = Integer.parseInt(words[1]) - 1;
                records[listIndex].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:\n" + records[listIndex]);
                break;
            case "todo":
                // Adding a new todo task
                String todoDescription = line.substring(5); // Extracting description
                records[numItems] = new Todo(todoDescription);
                System.out.println("Got it. I've added this task:\n" + records[numItems]);
                numItems++;
                break;
            case "deadline":
                // Adding a new deadline task
                String deadlineDescription = line.substring(9); // Extracting description
                String[] deadlineParts = deadlineDescription.split(" /by ");
                records[numItems] = new Deadline(deadlineParts[0], deadlineParts[1]);
                System.out.println("Got it. I've added this task:\n" + records[numItems]);
                numItems++;
                break;
            case "event":
                // Adding a new event task
                String eventDescription = line.substring(6); // Extracting description
                String[] eventParts = eventDescription.split(" /from | /to ");
                records[numItems] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                System.out.println("Got it. I've added this task:\n" + records[numItems]);
                numItems++;
                break;
            default:
                // Invalid command
                System.out.println("Sorry, I don't understand that command.");
            }
            System.out.println("____________________________________________________________");
            line = in.nextLine();
        }
        // Exiting message
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
