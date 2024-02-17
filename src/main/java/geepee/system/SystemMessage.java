package geepee.system;

public abstract class SystemMessage {

    public static final String TODO_TEMPLATE = "\"todo {description}\"";
    public static final String DEADLINE_TEMPLATE = "\"deadline {description} /by {deadline}\"";
    public static final String EVENT_TEMPLATE = "\"event {description} /from {from} /to {to}\"";

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
        System.out.println("    Invalid command! Valid commands are: todo, event, deadline, list");
        printHorizontalLine();
    }

    public static void printEmptyTodoDescriptionMessage() {
        printHorizontalLine();
        System.out.println("    The description of a todo cannot be empty! The correct input is " +
                TODO_TEMPLATE);
        printHorizontalLine();
    }

    public static void printEmptyDeadlineDescriptionMessage() {
        printHorizontalLine();
        System.out.println("    The description of a deadline cannot be empty! The correct input is " +
                DEADLINE_TEMPLATE);
        printHorizontalLine();
    }

    public static void printMissingDeadlineMessage() {
        printHorizontalLine();
        System.out.println("    The deadline of the task is missing! The correct input is " +
                DEADLINE_TEMPLATE);
        printHorizontalLine();
    }

    public static void printEmptyEventDescriptionMessage() {
        printHorizontalLine();
        System.out.println("    The description of an event cannot be empty! The correct input is " +
                EVENT_TEMPLATE);
        printHorizontalLine();
    }

    public static void printMissingFromMessage() {
        printHorizontalLine();
        System.out.println("    The start (from) of the event is missing! The correct input is " +
                EVENT_TEMPLATE);
        printHorizontalLine();
    }

    public static void printMissingToMessage() {
        printHorizontalLine();
        System.out.println("    The end (to) of the event is missing! The correct input is " +
                EVENT_TEMPLATE);
        printHorizontalLine();
    }

    public static void printFileNotFoundMessage() {
        printHorizontalLine();
        System.out.println("    No file found! The initialised list will be empty.");
    }

    public static void printIOExceptionMessage() {
        printHorizontalLine();
        System.out.println("    Unable to overwrite file!");
        printHorizontalLine();
    }
}
