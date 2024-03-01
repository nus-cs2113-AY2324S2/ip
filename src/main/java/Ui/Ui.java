package Ui;

public class Ui {
    private static final String BOT_NAME = "Davvy";

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printStatement(String statementType) {
        switch (statementType) {
        case "greetings":
            printLine();
            System.out.println(" Hello! I'm " + BOT_NAME + "\n" + " What can I do for you?");
            printLine();
            break;
        case "goodbye":
            System.out.println(" Bye. Hope to see you again soon!");
            printLine();
            break;
        }
    }
}
