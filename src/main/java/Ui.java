import java.util.Scanner;

public class Ui {
    // ASCII Art Generated from: https://patorjk.com/software/taag/#p=display&f=Big&t=anonBot
    private static final String LOGO = "                          ____        _   " + System.lineSeparator()
            + "                         |  _ \\      | |  " + System.lineSeparator()
            + "   __ _ _ __   ___  _ __ | |_) | ___ | |_ " + System.lineSeparator()
            + "  / _` | '_ \\ / _ \\| '_ \\|  _ < / _ \\| __|" + System.lineSeparator()
            + " | (_| | | | | (_) | | | | |_) | (_) | |_ " + System.lineSeparator()
            + "  \\__,_|_| |_|\\___/|_| |_|____/ \\___/ \\__|" + System.lineSeparator();
    private static final String SECTION_BAR = "____________________________________________________________";

    private static final Scanner userInput = new Scanner(System.in);

    /**
     * Prints greeting messages.
     */
    public static void printGreetings() {
        System.out.println(LOGO);
        printSectionBar(false);
        System.out.println("Hello! I'm anonBot");
        System.out.println("What can I do for you?");
        printSectionBar(true);
    }

    /**
     * Prints goodbye message.
     */
    public static void printGoodbye() {
        System.out.println("See ya!");
    }

    public static void printSectionBar(boolean withLineTerminator){
        if (withLineTerminator){
            System.out.println(SECTION_BAR + System.lineSeparator());
        } else {
            System.out.println(SECTION_BAR);
        }
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
