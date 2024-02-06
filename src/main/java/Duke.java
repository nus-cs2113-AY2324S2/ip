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

            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0].toLowerCase();
            String taskString = splitInput.length > 1 ? splitInput[1] : "";

            switch (command) {
            case "bye":
                // Exit if input is "bye"
                isRunning = false;
                break;
            case "list":
                // Print the list if input is "list"
                taskList.printList();
                break;

            case "mark":
                // Mark a task as done if input is "mark"
                int index = Integer.parseInt(splitInput[1]);
                taskList.setItemStatus(index, true);
                break;
            case "unmark":
                // Mark a task as not done if input is "unmark"
                index = Integer.parseInt(splitInput[1]);
                taskList.setItemStatus(index, false);
                break;

            case "todo":
                // Add a todo task if input starts with "todo"
                taskList.addItem(taskString, "T");
                break;
            case "deadline":
                // Add a deadline task if input starts with "deadline"
                taskList.addItem(taskString, "D");
                break;
            case "event":
                // Add an event task if input starts with "event"
                taskList.addItem(taskString, "E");
                break;

            default:
                // Add default task to the list (no type)
                taskList.addItem(input);
                break;
            }
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
