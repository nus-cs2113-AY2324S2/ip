package uwunzhe.util;

import java.util.Scanner;
import uwunzhe.tasks.Task;

public final class Ui {
    // Print Constants
    private final static String botName = "Uwunzhe";
    private final static String logo = " _    _                          _          \n"
            + "| |  | |                        | |         \n"
            + "| |  | |_      ___   _ _ __  ___| |__   ___ \n"
            + "| |  | \\ \\ /\\ / / | | | '_ \\|_  / '_ \\ / _ \\\n"
            + "| |__| |\\ V  V /| |_| | | | |/ /| | | |  __/\n"
            + " \\____/  \\_/\\_/  \\__,_|_| |_/___|_| |_|\\___|\n";

    private final static String lineString = "-".repeat(60);

    // Scanner object to get user input
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints a string.
     * 
     * @param s The string to be printed.
     */
    public static void print(String s) {
        System.out.print(s);
    }

    /**
     * Prints the string literal of a task.
     * 
     * @param task The task to be printed, of type {@link Task}.
     */
    public static void printTask(Task task) {
        System.out.print(task);
    }

    /**
     * Prints the string literal of a task with leading text.
     * 
     * @param task The task to be printed, of type {@link Task}.
     * @param leading The leading text.
     */
    public static void printTask(Task task, String leading) {
        System.out.print(leading);
        System.out.print(task);
    }

    /**
     * Prints the string literal of a task at the specified index.
     * 
     * @param taskList The list of tasks, of type {@link TaskList}.
     * @param index The index of the task in the list.
     */
    public static void printTask(TaskList taskList, int index) {
        System.out.print(taskList.getTask(index));
    }

    /**
     * Prints the string literal of a task at the specified index with leading text.
     * 
     * @param taskList The list of tasks, of type {@link TaskList}.
     * @param index The index of the task in the list.
     * @param leading The leading text.
     */
    public static void printTask(TaskList taskList, int index, String leading) {
        System.out.print(leading);
        System.out.print(taskList.getTask(index));
    }

    /**
     * Prints a string with a newline character.
     * 
     * @param s The string to be printed.
     */
    public static void println(String s) {
        System.out.println(s);
    }

    /**
     * Prints the string literal of a task with a newline character.
     * 
     * @param task The task to be printed, of type {@link Task}.
     */
    public static void printlnTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints the string literal of a task with leading text and a newline character.
     * 
     * @param task The task to be printed, of type {@link Task}.
     * @param leading The leading text.
     */
    public static void printlnTask(Task task, String leading) {
        System.out.print(leading);
        System.out.println(task);
    }

    /**
     * Prints the string literal of a task at the specified index with a newline character.
     * 
     * @param taskList The list of tasks, of type {@link TaskList}.
     * @param index The index of the task in the list.
     */
    public static void printlnTask(TaskList taskList, int index) {
        System.out.println(taskList.getTask(index));
    }

    /**
     * Prints the string literal of a task at the specified index with leading text and a newline character.
     * 
     * @param taskList The list of tasks, of type {@link TaskList}.
     * @param index The index of the task in the list.
     * @param leading The leading text.
     */
    public static void printlnTask(TaskList taskList, int index, String leading) {
        System.out.print(leading);
        System.out.println(taskList.getTask(index));
    }

    /**
     * Prints the logo of the bot.
     */
    public void printLogo() {
        println(logo);
        addLineBreak();
    }

    /**
     * Prints a horizontal line.
     */
    public void addLineBreak() {
        println(lineString);
    }

    /**
     * Prints the logo and a welcome message.
     */
    public void printInitMsg() {
        printLogo();
        print("HELLO MY POSITIVE MENTALITY FLEN!! MY NAME IS ");
        println(botName.toUpperCase() + "!!!");
        println("Actually uh... What even do you want me to do?");
    }

    /**
     * Prints a goodbye message.
     */
    public void printExitMsg() {
        println("Good night my positive mentality flen, "
                + "it is time for me to take my happy pills. Bye Bye!");
        addLineBreak();
    }

    /**
     * Gets user input.
     * 
     * @return The user input as a string.
     */
    public String getInput() {
        addLineBreak();
        System.out.print(": ");
        String input = this.sc.nextLine();
        addLineBreak();
        return input;
    }

    /**
     * Gets user input.
     * 
     * @param leadingString The string to print before the user input.
     * @return The user input as a string.
     */
    public String getInput(String leadingString) {
        addLineBreak();
        System.out.print(leadingString);
        String input = sc.nextLine();
        addLineBreak();
        return input;
    }
    
    /**
     * Closes the scanner object.
     */
    public void closeScanner() {
        this.sc.close();
    }
}
