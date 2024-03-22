import java.util.Scanner;
/**
 * Represents the user interface for interacting with the Dul application.
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| |\n"
                + "| | | | | | | |\n"
                + "| |_| | |_| | |\n"
                + "|____/ \\__,_|_|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
