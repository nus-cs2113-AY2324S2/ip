import java.util.Scanner;

public class Ui {
    // ASCII Art Generated from: https://patorjk.com/software/taag/#p=display&f=Big&t=anonBot
    public static final String LOGO = "                          ____        _   \n"
            + "                         |  _ \\      | |  \n"
            + "   __ _ _ __   ___  _ __ | |_) | ___ | |_ \n"
            + "  / _` | '_ \\ / _ \\| '_ \\|  _ < / _ \\| __|\n"
            + " | (_| | | | | (_) | | | | |_) | (_) | |_ \n"
            + "  \\__,_|_| |_|\\___/|_| |_|____/ \\___/ \\__|\n";
    public static final String SECTION_BAR = "____________________________________________________________";

    private static final Scanner userInput = new Scanner(System.in);

    /**
     * Prints greeting messages.
     */
    public static void printGreetings() {
        System.out.println(LOGO);
        System.out.println(SECTION_BAR);
        System.out.println("Hello! I'm anonBot");
        System.out.println("What can I do for you?");
        System.out.println(SECTION_BAR + "\n");
    }

    /**
     * Prints goodbye message.
     */
    public static void printGoodbye() {
        System.out.println(SECTION_BAR);
        System.out.println("See ya!");
        System.out.println(SECTION_BAR);
    }

    public static String getUserInput() {
        // Adapted from: https://stackoverflow.com/questions/13729294/nosuchelementexception-with-java-util-scanner
        if (userInput.hasNextLine()) {
            return userInput.nextLine();
        } else {
            return "";
        }
    }
}
