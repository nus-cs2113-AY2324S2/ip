package geepee.system;

public class SystemMessage {

    public static void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }

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

    public static void printInvalidCommandMessage() {
        printHorizontalLine();
        System.out.println("    Invalid command! Valid commands are: todo, event, deadline, list.");
        printHorizontalLine();
    }

    public static void printEmptyDescriptionMessage() {
        printHorizontalLine();
        System.out.println("    The description of a task cannot be empty! The correct input" +
                " is \"{command} {description}\".");
        printHorizontalLine();
    }
}
