import java.util.Scanner;

public class Ui {
    private static final String NAME = "Jonas";
    private static final String LINE = "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME + ".");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showByeMessage() {
        System.out.println(LINE);
        System.out.println("Kamxia. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showError(String errorMessage) {
        System.out.println(LINE);
        System.out.println("OOPS!!! " + errorMessage);
        System.out.println(LINE);
    }

    public void closeScanner() {
        scanner.close();
    }
}
