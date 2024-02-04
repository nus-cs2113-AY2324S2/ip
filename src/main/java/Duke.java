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
        String[] records = new String[100];
        int numItems = 0; // Number of tasks

        // Main loop
        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________");

            // Checking user input
            if ("list".equals(line)) {
                // Listing tasks
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < numItems; j++) {
                    System.out.println((j + 1) + "." + records[j]);
                }
            } else {
                records[numItems] = line;
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
