package geepee.system;

public class SystemMessage {
    
    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("    Hello! I'm GeePee, your friendly chatbot assistant!");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }

    public static void printExitMessage() {
        printHorizontalLine();
        System.out.println("    Bye! Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }
}
