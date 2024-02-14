public class Parser {
    public static void printMessageBorder() {
        System.out.println("\t--------------------------------------------------");
    }

    public static void printWelcomeMessage() {
        String welcome = "Hello! I'm Misty\n"
                + "\tWhat can I do for you?";

        printMessageBorder();
        System.out.println("\t" + welcome);
        printMessageBorder();
    }

    public static void printByeMessage() {
        String bye = "Bye! Hope to see you again soon!";

        printMessageBorder();
        System.out.println("\t" + bye);
        printMessageBorder();
    }

    public static void printUnknownCommandMessage() {
        String bye = "Sorry, unknown command entered!";
        System.out.println("\t" + bye);
    }
}
