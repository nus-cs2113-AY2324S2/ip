package Chat.tasks;

import java.util.Scanner;
public class Ui {
    private static final String LINE_PREFIX = "    ";
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets user input.
     * @return The input as string type.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Show a welcome message to the user.
     */
    public void showWelcomeMessage() {
        showLine();
        System.out.println("Hello! I'm Evelyn");
        System.out.println("What can I do for you?");
        showLine();
    }
    /**
     * Show a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    
}
