package timl.utility;
import timl.task.Task;
import timl.task.TaskManager;

/**
 * Handles output messages to the user
 */
public class Printer {

    protected static final String LINE = "    ____________________________________________________________\n";
    protected static final String GREETING = LINE +
            "     Hello! I'm TimL\n" +
            "     What can I do for you?\n" + LINE;
    protected static final String GOODBYE = LINE + "     Bye. Hope to see you again soon!\n" + LINE;
    protected static final String LIST = LINE + "     Here's your List DOOD:";
    protected static final String DEFAULT_ERROR = LINE +
            "     OOF that's an Invalid message :(\n" + LINE;
    protected static final String FOUND_NOTHING = LINE + "     SIR I FOUND NOTHING :(\n" + LINE;
    protected static final String INVALID_MESSAGE = LINE +
            "     OOF that's an Invalid number :(\n" + LINE;
    protected static final String INVALID_EVENT = LINE +
            "     SORRY THAT IS AN INVALID EVENT WITH MISSING PARAMS :(\n" + LINE;
    protected static final String INVALID_DEADLINE = LINE +
            "     SORRY THAT IS AN INVALID DEADLINE WITH MISSING PARAMS :(\n" + LINE;
    protected static final String FIND_STATEMENT = LINE +
            "     HERE ARE YOUR MATCHING TASKS IN YOUR LIST: ";
    protected static final String DELETE_ERROR = LINE +
            "     SORRY I CAT DELETE THAT TASK. YOUR PARAMS MUST BE WRONG.\n" + LINE;
    protected static final String INVALID_TASKOVERFLOW = LINE +
            "     SORRY TASK MANAGER HAS FULL CAPACITY :(\n" + LINE;
    protected static final String EMPTY_CONTENT = LINE +
            "     Your task description is empty :-(\n" + LINE;
    protected static final String IO_EXCEPTION = LINE +
            "     IO-exception sir :-(\n" + LINE;
    protected static final String DELETE_OPENING = LINE +
            "     Your task as shown BELOW has sadly been deleted :-(";

    public static void printGreeting() {
        System.out.print(GREETING);
    }

    public static void printGoodbye() {
        System.out.print(GOODBYE);
    }

    public static void printList() {
        System.out.println(LIST);
        for (Task i : TaskManager.getList()) {
            System.out.println("    " + (TaskManager.getList().indexOf(i) + 1) + ": " + i.getStatus());
        }
        Printer.printLine();
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void printMarkedMessage(int index) {
        System.out.println("    ____________________________________________________________\n" +
                "    Nice!! I've marked this task as done:");
        System.out.println("    [X] " + TaskManager.getList().get(index).getDescription());
        Printer.printLine();
    }

    public static void printUnMarkedOpening(int index) {
        System.out.println("    ____________________________________________________________\n" +
                "    Awh :( I've unmarked this task");
        System.out.println("    [ ] " + TaskManager.getList().get(index).getDescription());
        Printer.printLine();
    }

    public static void printDefaultError() {
        System.out.print(DEFAULT_ERROR);
    }

    public static void printAddedTaskOpening() {
        System.out.println(LINE + "    Roger that! i've added this task:");
    }

    public static void printInvalidNumber() {
        System.out.print(INVALID_MESSAGE);
    }

    public static void printTaskOverflow() {
        System.out.print(INVALID_TASKOVERFLOW);
    }

    public static void printInvalidEvent() {
        System.out.print(INVALID_EVENT);
    }

    public static void printInvalidDeadline() {
        System.out.print(INVALID_DEADLINE);
    }

    public static void printDeleteOpening() {
        System.out.println(DELETE_OPENING);
    }

    public static void printEmptyTodoCommand() {
        System.out.print(EMPTY_CONTENT);
    }

    public static void printIOException() {
        System.out.print(IO_EXCEPTION);
    }

    public static void printInvalidDelete() {
        System.out.print(DELETE_ERROR);
    }

    public static void printFindOpening() {
        System.out.println(FIND_STATEMENT);
    }

    public static void printFoundNothingMessage() {
        System.out.print(FOUND_NOTHING);
    }

    public static void printAddedTaskMessage(String description, int size) {
        Printer.printAddedTaskOpening();
        System.out.println("    " + description);
        System.out.println("    Now you have " + size + " task in the list.");
        Printer.printLine();
    }

    public static void printDeleteTaskMessage(String description, int size) {
        Printer.printDeleteOpening();
        System.out.println("     " + description);
        System.out.println("     Now you have " + (size - 1) + " task in the list.");
        Printer.printLine();
    }
}


