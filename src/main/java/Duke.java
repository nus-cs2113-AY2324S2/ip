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

        // Main loop
        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
            line = in.nextLine();
        }
        // Exiting message
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
