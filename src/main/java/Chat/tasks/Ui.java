package Chat.tasks;

import java.util.Scanner;
public class Ui {
    private static final String LINE_PREFIX = "    ";
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        showLine();
        System.out.println("Hello! I'm Evelyn");
        System.out.println("What can I do for you?");
        showLine();
    }

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

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskList(String taskList) {
        System.out.println(taskList);
    }
}
