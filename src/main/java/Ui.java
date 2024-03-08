import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * print welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Venti.");
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * print a line
     */
    public void printLine()
    {
        System.out.println("____________________________________________________________");
    }

}
