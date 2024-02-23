package geepee.system;

public abstract class SystemMessage {

    public static final String TODO_TEMPLATE = "\"todo {description}\"";
    public static final String DEADLINE_TEMPLATE = "\"deadline {description} /by {deadline}\"";
    public static final String EVENT_TEMPLATE = "\"event {description} /from {from} /to {to}\"";

    public static String getHorizontalLine() {
        return "    ________________________________________________";
    }

    public static void printWelcomeMessage() {
        System.out.println(getHorizontalLine());
        System.out.println("    Hello! I'm GeePee, your friendly chatbot assistant!");
        System.out.println("    What can I do for you?");
        System.out.println(getHorizontalLine());
    }

    public static void printExitMessage() {
        System.out.println(getHorizontalLine());
        System.out.println("    Bye! Hope to see you again soon!");
        System.out.println(getHorizontalLine());
    }

    public static void printFileNotFoundMessage() {
        System.out.println(getHorizontalLine());
        System.out.println("    No file found! The initialised list will be empty.");
    }

    public static void printIOExceptionMessage() {
        System.out.println(getHorizontalLine());
        System.out.println("    Unable to overwrite file!");
        System.out.println(getHorizontalLine());
    }
}
