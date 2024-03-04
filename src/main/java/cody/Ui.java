package cody;

import java.util.Scanner;

public class Ui {
    // Constants for repeated strings
    private static final String BORDER = "______________________________________________________________\n";
    private static final String GREET_MESSAGE = " Hey there! I'm Cody, your personal task manager\n"
            + " Tell me your tasks and I will create a task list for you";
    private static final String EXIT_MESSAGE = " Bye. Hope to see you again soon!";

    public static void greet() {
        printMessage(GREET_MESSAGE);
    }

    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void printMessage(String message) {
        System.out.print(BORDER + message + "\n" + BORDER);
    }

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void exit() {
        printMessage(EXIT_MESSAGE);
    }
}
