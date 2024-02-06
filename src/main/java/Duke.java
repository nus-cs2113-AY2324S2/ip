import java.util.Scanner;

public class Duke {
    // Print Constants
    private static String botName = "Uwunzhe";
    private static String logo = " _    _                          _          \n"
            + "| |  | |                        | |         \n"
            + "| |  | |_      ___   _ _ __  ___| |__   ___ \n"
            + "| |  | \\ \\ /\\ / / | | | '_ \\|_  / '_ \\ / _ \\\n"
            + "| |__| |\\ V  V /| |_| | | | |/ /| | | |  __/\n"
            + " \\____/  \\_/\\_/  \\__,_|_| |_/___|_| |_|\\___|\n";

    private static String lineString = "-".repeat(60);

    // Scanner object to get user input
    private static Scanner sc = new Scanner(System.in);

    /**
     * Prints the logo of the bot.
     * 
     * @param None
     * @return None
     */
    public static void displayLogo() {
        System.out.println(logo);
        addLineBreak();
    }

    /**
     * Prints a horizontal line.
     * 
     * @param None
     * @return None
     */
    public static void addLineBreak() {
        System.out.println(lineString);
    }

    /**
     * Initializes the bot, prints the logo and a welcome message.
     * 
     * @param None
     * @return None
     */
    public static void initialize() {
        displayLogo();
        System.out.print("HELLO MY POSITIVE MENTALITY FLEN!! MY NAME IS ");
        System.out.println(botName.toUpperCase() + "!!!");
        System.out.println("Actually uh... What even do you want me to do?");
    }

    /**
     * Gets user input.
     * 
     * @param None
     * @return The user input.
     */
    public static String getInput() {
        addLineBreak();
        System.out.print(": ");
        String input = sc.nextLine();
        addLineBreak();
        return input;
    }

    /**
     * Gets user input.
     * Overloaded method.
     * 
     * @param leadingString The string to print before the user input.
     * @return The user input.
     */
    public static String getInput(String leadingString) {
        addLineBreak();
        System.out.print(leadingString);
        String input = sc.nextLine();
        addLineBreak();
        return input;
    }

    /**
     * The main loop of the bot, handles user input.
     * 
     * @param None
     * @return None
     */
    public static void loop() {
        boolean isRunning = true;
        List taskList = new List();

        while (isRunning) {
            String input = getInput();
            // TODO: Refactor below into function or improve using enum maybe?

            if (input.equalsIgnoreCase("bye")) {
                // Exit if input is "bye"
                isRunning = false;
                continue;

            } else if (input.equalsIgnoreCase("list")) {
                // Print the list if input is "list"
                taskList.printList();
                continue;

            } else if (input.startsWith("mark")) {
                // Mark a task as done if input is "mark"
                String[] splitInput = input.split(" ");
                int index = Integer.parseInt(splitInput[1]);
                taskList.setItemStatus(index, true);
                continue;
            } else if (input.startsWith("unmark")) {
                // Mark a task as not done if input is "unmark"
                String[] splitInput = input.split(" ");
                int index = Integer.parseInt(splitInput[1]);
                taskList.setItemStatus(index, false);
                continue;

            }  else if (input.startsWith("todo")) {
                // Add a todo task if input starts with "todo"
                String taskString = input.split(" ", 2)[1];
                taskList.addItem(taskString, "T");
                continue;
            } else if (input.startsWith("deadline")) {
                // Add a deadline task if input starts with "deadline"
                String taskString = input.split(" ", 2)[1];
                taskList.addItem(taskString, "D");
                continue;
            } else if (input.startsWith("event")) {
                // Add an event task if input starts with "event"
                String taskString = input.split(" ", 2)[1];
                taskList.addItem(taskString, "E");
                continue;
            }
            
            // Add default task to the list (no type)
            taskList.addItem(input);
        }

        sc.close();
    }

    /**
     * Exits the bot, prints a goodbye message.
     * 
     * @param None
     * @return None
     */
    public static void exit() {
        System.out.println("Good night my positive mentality flen, "
                + "it is time for me to take my happy pills. Bye Bye!");
        addLineBreak();
    }

    public static void main(String[] args) {
        initialize();
        loop();
        exit();
    }
}
