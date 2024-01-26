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

        while (isRunning) {
            System.out.print(": ");
            String input = sc.nextLine();
            addLineBreak();

            // Exit if input is "bye"
            if (input.equalsIgnoreCase("bye")) {
                isRunning = false;
                sc.close();
                return;
            }
            
            // Echo the input if not exiting
            System.out.println(input);
            addLineBreak();
        }
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
