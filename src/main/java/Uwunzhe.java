import java.util.Scanner;

public class Uwunzhe {
    private static String botName = "Uwunzhe";
    private static String logo = " _    _                          _          \n"
            + "| |  | |                        | |         \n"
            + "| |  | |_      ___   _ _ __  ___| |__   ___ \n"
            + "| |  | \\ \\ /\\ / / | | | '_ \\|_  / '_ \\ / _ \\\n"
            + "| |__| |\\ V  V /| |_| | | | |/ /| | | |  __/\n"
            + " \\____/  \\_/\\_/  \\__,_|_| |_/___|_| |_|\\___|\n";

    private static String lineString = "-".repeat(60);

    /**
     * Prints the logo of the bot.
     * 
     * @param None
     * @return None
     */
    public static void displayLogo() {
        System.out.println(logo);
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
        addLineBreak();

        System.out.print("HELLO MY POSITIVE MENTALITY FLEN!! MY NAME IS ");
        System.out.println(botName.toUpperCase() + "!!!");
        System.out.println("Actually uh... What even do you want me to do?");

        addLineBreak();
    }

    /**
     * The main loop of the bot, handles user input.
     * 
     * @param None
     * @return None
     */
    public static void loop() {
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        List taskList = new List();

        while (isRunning) {
            System.out.print(": ");
            String input = sc.nextLine();
            addLineBreak();

            if (input.equalsIgnoreCase("bye")) {
                // Exit if input is "bye"
                isRunning = false;
                continue;
            } else if (input.equalsIgnoreCase("list")) {
                // Print the list if input is "list"
                taskList.printList();
                addLineBreak();
                continue;
            }
            
            // Echo the input if not exiting
            System.out.print("added: ");
            System.out.println(input);
            // Add task to the list
            taskList.addItem(input);
            addLineBreak();
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
