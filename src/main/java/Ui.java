import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.err.println("Error loading tasks from file.");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Brennan!");
        System.out.println("What can I do for you?\n");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}
