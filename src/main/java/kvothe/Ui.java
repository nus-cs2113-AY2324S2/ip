package kvothe;

import kvothe.exception.WrongArgumentsException;

import java.util.Scanner;
import kvothe.task.Task;

/**
 * Represents the user interface of the application
 */
public class Ui {

    private String lastLine;

    private static final String LOGO = "\n" +
            "\t\t██╗░░██╗██╗░░░██╗░█████╗░████████╗██╗░░██╗███████╗\n" +
            "\t\t██║░██╔╝██║░░░██║██╔══██╗╚══██╔══╝██║░░██║██╔════╝\n" +
            "\t\t█████═╝░╚██╗░██╔╝██║░░██║░░░██║░░░███████║█████╗░░\n" +
            "\t\t██╔═██╗░░╚████╔╝░██║░░██║░░░██║░░░██╔══██║██╔══╝░░\n" +
            "\t\t██║░╚██╗░░╚██╔╝░░╚█████╔╝░░░██║░░░██║░░██║███████╗\n" +
            "\t\t╚═╝░░╚═╝░░░╚═╝░░░░╚════╝░░░░╚═╝░░░╚═╝░░╚═╝╚══════╝\n";
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
        System.out.println(LOGO);
        echo("Hello! I'm " + NAME + "\n\t\tWhat can I do for you?", true, false);
    }

    /**
     * Prints the goodbye message
     */
    public void showGoodbye() {
        echo("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the next command from the user
     * @return the command as a string
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        this.lastLine = in.nextLine();
        return this.lastLine;
    }

    /**
     * Prints the message after deleting a task
     * @param task task that has been deleted
     * @param size size of the list after deletion
     */
    public void showDelete(Task task, int size) {
        echo("Noted. I've removed this task:\n\t\t" + task, true, false);
        echo("Now you have " + size + " tasks in the list.", false, true);

    }

    /**
     *
     * @return the last line read
     */
    public String geLastLine() {
        return lastLine;
    }

    /**
     * Show Exit message
     */
    public void showExit() {
        echo("Bye. Hope to see you again soon!");
    }


}
