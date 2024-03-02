package burger.UI;

import burger.BurgerException;

public class Utilities {

    public static final String HORIZONTAL_LINE = "---------------------------------";
    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void welcomeMessage(String name) {
        printLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
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

    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
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
        System.out.println("Tsk... Out of index, do you know how to count?");
        printLine();
    }
}
