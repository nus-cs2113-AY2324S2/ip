package kvothe;

import kvothe.exception.WrongArgumentsException;

import java.util.Scanner;
import kvothe.task.Task;

public class Ui {

    private String line;
    private static final String NAME = "Kvothe";
    private static final String DIVIDER = "\t\t____________________________________________________________";

    public Ui() {
    }

    /**
     * Prints a line with indentation and optional top and bottom border
     * @param line the line to print
     * @param isTop true to print the top border
     * @param isBottom true to print the bottom border
     */
    public void echo(String line, boolean isTop, boolean isBottom) {
        if (isTop) {
            System.out.println(DIVIDER);
        }

        System.out.println("\t\t" + line);

        if (isBottom) {
            System.out.println(DIVIDER);
        }

    }

    /**
     * Prints a line with indentation
     * @param line the line to print
     */
    public void echo(String line) {
        System.out.println(DIVIDER);
        System.out.println("\t\t" + line);
        System.out.println(DIVIDER);

    }

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        echo("Hello! I'm " + NAME + "\n\t\tWhat can I do for you?", true, false);
    }

    /**
     * Prints the goodbye message
     */
    public void showGoodbye() {
        echo("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showDelete(Task task, int size) {
        echo("Noted. I've removed this task:\n\t\t" + task, true, false);
        echo("Now you have " + size + " tasks in the list.", false, true);

    }

    public String getLine() {
        return line;
    }


    public void showExit() {
        echo("Bye. Hope to see you again soon!");
    }


}
