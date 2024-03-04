package cody.ui;

import java.util.Scanner;

public class Ui {
    private static final String BORDER = "______________________________________________________________\n";
    private static final String GREET_MESSAGE = " Hey there! I'm Cody, your personal task manager\n"
            + " Tell me your tasks and I will create a task list for you";
    private static final String EXIT_MESSAGE = " Bye. Hope to see you again soon!";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void greet() {
        printMessage(GREET_MESSAGE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.print(BORDER + message + "\n" + BORDER);
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void exit() {
        printMessage(EXIT_MESSAGE);
        scanner.close();
    }
}

