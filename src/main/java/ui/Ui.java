package ui;

import java.util.Scanner;

/**
 * Represents the user interface for the JingHao chatbot.
 * Responsible for taking in user input, and displaying messages.
 */
public class Ui {
    private static final String LINE_SEP = "____________________________________________________________";

    protected Scanner sc;

    /**
     * Constructs a new Ui object and initialises the Scanner to take in user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Greets the user upon starting the application.
     * Displays the welcome message to the user.
     */
    public void greetUser() {
        System.out.println(LINE_SEP + "\nHello! I'm JingHao" );
        System.out.println("What can I do for you?\n" + LINE_SEP);
    }

    /**
     * Reads in user's input using the Scanner.
     *
     * @return Returns the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the exit message upon exitting the application
     */
    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEP);
    }

    /**
     * Display the prompt for user's input.
     */
    public void promptUser() {
        System.out.print("Input: ");
    }

    /**
     * Display the message followed by a divider.
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
        System.out.println(LINE_SEP);
    }

    /**
     * Display a divider
     */
    public void printDivider() {
        System.out.println(LINE_SEP);
    }

    /**
     * Display the content without a divider.
     * @param input The content to be printed out.
     */
    public void printWithoutDivider(String input) {
        System.out.println(input);
    }

    /**
     * Display the error message
     * @param error The error message to be printed.
     */
    public void printErrorMessage(String error) {
        System.out.println(error);
        System.out.println(LINE_SEP);
    }

    /**
     * Displays the total number of task in the list of the chatbot.
     * @param total The total number of tasks in the list.
     */
    public void printTotalTask(int total){
        System.out.println("Now you have " + total + " tasks in the list.");
        System.out.println(LINE_SEP);
    }
}

