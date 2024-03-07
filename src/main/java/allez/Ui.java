package allez;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {

    private final Scanner in;
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";


    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads the line inputted by user.
     * Ignores pure whitespace, empty and comment lines.
     *
     * @return currentLine line entered by user
     */
    public String getUserCommand() {
        String currentLine =  in.nextLine();

        while(shouldIgnore(currentLine)) {
            currentLine = in.nextLine();
        }
        return currentLine;
    }

    private boolean shouldIgnore(String currentLine) {
        return currentLine.isBlank() || currentLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Prints exit message.
     */
    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints greeting message.
     */
    public static void printGreeting() {
        printHyphens();
        System.out.println("Hello! I'm Allez");
        System.out.println("What can I do for you?");
    }

    /**
     * Print hyphens to act as divider.
     */
    public static void printHyphens() {
        System.out.println("__________________________________________________");
    }
}
