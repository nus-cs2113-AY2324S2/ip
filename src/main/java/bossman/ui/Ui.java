package bossman;
public class Ui {
    private static final String BOT_NAME = "BossMan\n";
    private static final String SEP = "____________________________________________________________";

    public static void greetUser() {
        System.out.println(SEP + "\nHello! I'm BossMan");
        System.out.println("What can I do for you?\n" + SEP);
    }

    public static void printUser() {
        System.out.print("You:");
    }

    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n" + SEP);
    }

    public static void printMessage(String message) {
        System.out.println(message + "\n" + SEP);
    }

    public static void printSep() {
        System.out.println(SEP);
    }
}
