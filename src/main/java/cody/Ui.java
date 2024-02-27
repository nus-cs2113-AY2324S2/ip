package cody;

public class Ui {
    // Constants for repeated strings
    private static final String BORDER = "______________________________________________________________\n";
    private static final String GREET_MESSAGE = " Hey there! I'm Cody, your personal task manager\n"
            + " Tell me your tasks and I will create a task list for you\n";
    private static final String EXIT_MESSAGE = " Bye. Hope to see you again soon!\n";

    public static void printMessage(String message) {
        System.out.print(BORDER + message + BORDER);
    }

    // greets user
    public static void greet() {
        printMessage(GREET_MESSAGE);
    }

    // exits the program
    public static void exit() {
        printMessage(EXIT_MESSAGE);
    }
}
