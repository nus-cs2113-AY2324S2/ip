package bossman.ui;
public class Ui {
    private static final String BOT_NAME = "BossMan";
    private static final String SEP = "____________________________________________________________";

    public static void greetUser() {
        System.out.println(SEP + "\nHello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?\n" + SEP);
    }

    public static void printUser() {
        System.out.print("You:");
    }

    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n" + SEP);
    }

    public static void printMessageWithSepNewLine(String message) {
        System.out.println(message + "\n" + SEP);
    }

    public static void printMessageNoSepSameLine(String message) {
        System.out.print(message);
    }

    public static void printMessageNoSepNewLine(String message) {
        System.out.println(message);
    }

    public static void printNewLineWithSep(){
        System.out.println("\n" + SEP);
    }

    public static void printSep() {
        System.out.println(SEP);
    }


}
