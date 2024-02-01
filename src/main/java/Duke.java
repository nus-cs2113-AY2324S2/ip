import java.util.Scanner;

public class Duke {
    // ASCII Art Generated from: https://patorjk.com/software/taag/#p=display&f=Big&t=anonBot
    private static final String LOGO = "                          ____        _   \n"
            + "                         |  _ \\      | |  \n"
            + "   __ _ _ __   ___  _ __ | |_) | ___ | |_ \n"
            + "  / _` | '_ \\ / _ \\| '_ \\|  _ < / _ \\| __|\n"
            + " | (_| | | | | (_) | | | | |_) | (_) | |_ \n"
            + "  \\__,_|_| |_|\\___/|_| |_|____/ \\___/ \\__|\n";
    private static final String SECTION_BAR = "____________________________________________________________";

    /**
     * Takes user inputs as commands and process them.
     * If the input is not one of the supported commands below, the input shall be seen as a new task.
     * Tasks are NOT saved when the program exits.
     * Possible commands:
     * 1. `bye` - Exits the program.
     * 2. `list` - Lists out all the tasks.
     */
    private static void parseUserInputs() {
        Scanner in = new Scanner(System.in);
        String userInput;
        String[] tasks = new String[128];
        int taskCounter = 1;

        while (true) {
            userInput = in.nextLine();
            switch (userInput) {
            case "bye":
                return;
            case "list":
                System.out.println(SECTION_BAR);
                for (int i = 1; i < taskCounter; i++) {
                    System.out.format("%d. %s\n", i, tasks[i]);
                }
                System.out.println(SECTION_BAR + "\n");
                break;
            default:
                tasks[taskCounter] = userInput;
                taskCounter++;
                System.out.println(SECTION_BAR);
                System.out.println("Added: " + userInput);
                System.out.println(SECTION_BAR + "\n");

            }
        }
    }

    /**
     * Prints greeting messages.
     */
    private static void printGreetings() {
        System.out.println(LOGO);
        System.out.println(SECTION_BAR);
        System.out.println("Hello! I'm anonBot");
        System.out.println("What can I do for you?");
        System.out.println(SECTION_BAR + "\n");
    }

    /**
     * Prints goodbye message.
     */
    private static void printGoodbye() {
        System.out.println("Bye. See ya!");
        System.out.println(SECTION_BAR);
    }

    /**
     * Greets the user and exits.
     *
     * @param args List of command-line arguments
     */
    public static void main(String[] args) {
        printGreetings();
        parseUserInputs();
        printGoodbye();
    }
}
