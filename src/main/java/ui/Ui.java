package ui;

import java.util.Scanner;
import taskList.TaskList;


public class Ui {
    private Scanner scanner;
    private static final String HORIZONTAL = "____________________________________________________________";
    private static final String BOT_NAME = "Kiku";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greetingMessage() {
        System.out.println(HORIZONTAL);
        System.out.println("Hello! I'm " + BOT_NAME
                + "! How can I assist you today?");
        System.out.println(HORIZONTAL);
    }

    public void exitMessage() {
        System.out.println("Goodbye! " +
                "Have a good day and hope to see you again soon :)");
    }

    public void errorMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTask(String task) {
        System.out.println(task);
    }

    public void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list: ");
        System.out.println(taskList.getFormattedTasks());
    }
}
