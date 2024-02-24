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
     * @param s
     * @return None
     */
    public static void print(String s) {
        System.out.print(s);
    }

    /**
     * Prints the string literal of a task.
     * 
     * @param task
     * @return None
     */
    public static void printTask(Task task) {
        System.out.print(task);
    }

    /**
     * Prints the string literal of a task with leading text.
     * 
     * @param task
     * @param leading
     * @return None
     */
    public static void printTask(Task task, String leading) {
        System.out.print(leading);
        System.out.print(task);
    }

    /**
     * Prints the string literal of a task at the specified index.
     * 
     * @param taskList
     * @param index
     * @return None
     */
    public static void printTask(TaskList taskList, int index) {
        System.out.print(taskList.getTask(index));
    }

    /**
     * Prints the string literal of a task at the specified index with leading text.
     * 
     * @param taskList
     * @param index
     * @param leading
     * @return None
     */
    public static void printTask(TaskList taskList, int index, String leading) {
        System.out.print(leading);
        System.out.print(taskList.getTask(index));
    }

    /**
     * Prints a string with a newline character.
     * 
     * @param s
     * @return None
     */
    public static void println(String s) {
        System.out.println(s);
    }

    /**
     * Prints the string literal of a task with a newline character.
     * 
     * @param task
     * @return None
     */
    public static void printlnTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints the string literal of a task with leading text and a newline character.
     * 
     * @param task
     * @param leading
     * @return None
     */
    public static void printlnTask(Task task, String leading) {
        System.out.print(leading);
        System.out.println(task);
    }

    /**
     * Prints the string literal of a task at the specified index with a newline character.
     * 
     * @param taskList
     * @param index
     * @return None
     */
    public static void printlnTask(TaskList taskList, int index) {
        System.out.println(taskList.getTask(index));
    }

    /**
     * Prints the string literal of a task at the specified index with leading text and a newline character.
     * 
     * @param taskList
     * @param index
     * @param leading
     * @return None
     */
    public static void printlnTask(TaskList taskList, int index, String leading) {
        System.out.print(leading);
        System.out.println(taskList.getTask(index));
    }

    /**
     * Prints the logo of the bot.
     * 
     * @param None
     * @return None
     */
    public void printLogo() {
        println(logo);
        addLineBreak();
    }

    /**
     * Prints a horizontal line.
     * 
     * @param None
     * @return None
     */
    public void addLineBreak() {
        println(lineString);
    }

    /**
     * Initializes the bot, prints the logo and a welcome message.
     * 
     * @param None
     * @return None
     */
    public void printInitMsg() {
        printLogo();
        print("HELLO MY POSITIVE MENTALITY FLEN!! MY NAME IS ");
        println(botName.toUpperCase() + "!!!");
        println("Actually uh... What even do you want me to do?");
    }

    /**
     * Exits the bot, prints a goodbye message.
     * 
     * @param None
     * @return None
     */
    public void printExitMsg() {
        println("Good night my positive mentality flen, "
                + "it is time for me to take my happy pills. Bye Bye!");
        addLineBreak();
    }

    /**
     * Gets user input.
     * 
     * @param None
     * @return The user input.
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
     * Overloaded method.
     * 
     * @param leadingString The string to print before the user input.
     * @return The user input.
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
     * 
     * @param None
     * @return None
     */
    public void closeScanner() {
        this.sc.close();
    }
}
