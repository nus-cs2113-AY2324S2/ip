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
            if ("list".equals(line)) {
                // Listing tasks
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < numItems; j++) {
                    System.out.println((j + 1) + "." + "[" + (records[j].isDone ? "X" : " ") + "]"
                            + records[j].description);
                }
            } else if (words[0].equals("mark")) {
                // Marking a task as done
                listIndex = Integer.parseInt(words[1]) - 1;
                records[listIndex].isDone = true;
                System.out.println("Nice! I've marked this task as done:\n" + "[x]"
                        + records[listIndex].description);
            } else if (words[0].equals("unmark")) {
                // Marking a task as not done
                listIndex = Integer.parseInt(words[1]) - 1;
                records[listIndex].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:\n" + "[ ]"
                        + records[listIndex].description);
            } else {
                // Adding a new task
                records[numItems] = new Task(line);
                System.out.println(line);
                numItems++;
            }
            System.out.println("____________________________________________________________");
            line = in.nextLine();
        }
        // Exiting message
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

