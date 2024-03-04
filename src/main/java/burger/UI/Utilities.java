package burger.UI;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Utilities {

    public static final String HORIZONTAL_LINE = "---------------------------------";
    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    private final Scanner in;
    private final PrintStream out;

    public Utilities() {
        this(System.in, System.out);
    }

    public Utilities(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserInput() {
        String input = in.nextLine();
        return input.trim();
    }


    public void welcomeMessage(String name) {
        printLine();
        out.println("Hello! I'm " + name);
        out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints error message when user provides unknown input
     */
    public static void printUnknownInputError() {
        printLine();
        System.out.println("Wake Up!!! Key in something that makes sense!");
        printLine();
    }

    public void goodbye() {
        printLine();
        out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printInvalidDeadlineInputMessage() {
        printLine();
        System.out.println("Oi, the input is wrong!");
        System.out.println("Format: deadline {deadline task} /by {date}");
        printLine();
    }

    public static void printInvalidEventInputMessage() {
        printLine();
        System.out.println("Oi, the input is wrong!");
        System.out.println("Format: event {event task} /from {date} /to {date}");
        printLine();
    }

    public static void printEmptyDescription() {
        printLine();
        System.out.println("UwU~ Where is your description?");
        System.out.println("Format: {command} {description}");
        printLine();
    }

    public static void printErrorMessage(BurgerException e) {
        printLine();
        System.out.println(e.getMessage());
        printLine();
    }
}
