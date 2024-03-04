import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.println("\nEnter your command:");
        return scanner.nextLine().trim();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm TaskManager\nWhat can I do for you?");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine(String message) {
        System.out.println(message);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
