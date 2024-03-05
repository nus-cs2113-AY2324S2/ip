import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm LeoDas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty list.");
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
