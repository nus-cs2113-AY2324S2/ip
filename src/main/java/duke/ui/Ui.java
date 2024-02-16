package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface for the Duke chatbot.
 * Responsible for taking in user input, and displaying messages.
 */
public class Ui {
    Scanner sc;
    static final String HORIZONTAL_LINE = "\t_____________________________________________________________________\n";

    /**
     * Constructs a new Ui object and initialises the Scanner for taking in user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Edits the given message into the default message format.
     *
     * @param message The message to be displayed.
     * @return Formatted message.
     */
    private String messageFormat(String message) {
        return HORIZONTAL_LINE + "\t " + message + System.lineSeparator() + HORIZONTAL_LINE;
    }

    /**
     * Displays the welcome message of the Duke chatbot.
     */
    public void printHi() {
        System.out.print(messageFormat("Awakening.... \n\t Hi! I'm Faiz!\n\t What can I do for you?"));
    }

    /**
     * Displays the exit message of the Duke chatbot.
     */
    public void printBye() {
        System.out.print(messageFormat("Deformation.... \n\t Bye! Hope to see you again soon!"));
    }

    /**
     * Reads in user input via the Scanner.
     * @return The user's input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Displays message of the executed commands.
     * @param message The message to be displayed.
     */
    public void printMessage(String message) {
        System.out.print(messageFormat(message));
    }

    /**
     * Displays the error message.
     * @param message The error message to be displayed.
     */
    public void printError(String message) {
        System.out.print(messageFormat(message));
    }
}
