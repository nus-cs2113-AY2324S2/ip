package humi;

public class Ui {
    public static final String LINE = "    ____________________________________________________________";

    public void printWelcome() {
        System.out.println(LINE);
        System.out.println("     Hello! I'm Humi");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
    }

    public void printExit() {
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
