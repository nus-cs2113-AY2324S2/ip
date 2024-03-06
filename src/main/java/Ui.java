import java.util.Scanner;

public class Ui {
    public void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm LeoDas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void line() {
        System.out.println("____________________________________________________________");
    }

    public void loading_error() {
        System.out.println("Error loading tasks from file. Starting with an empty list.");
    }

    public void error(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public String read_input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
