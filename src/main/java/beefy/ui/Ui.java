package beefy.ui;

public class Ui {
    private static final String BOT_NAME = "BEEFY\n";
    private static final String SEPARATION = "____________________________________________________________________\n";

    private static String messageFormat(String message) {
        return SEPARATION + BOT_NAME + message + System.lineSeparator() + SEPARATION;
    }

    public static void printHi() {
        System.out.print(messageFormat("Hello there, I'm Beefy, what can I do for you?"));
    }

    public static void printUser() {
        System.out.println("You:");
    }

    public static void printBye() {
        System.out.print(messageFormat("Good Bye, Hope to see you again!"));
    }

    public static void printMessage(String message) {
        System.out.print(messageFormat(message));
    }
}
