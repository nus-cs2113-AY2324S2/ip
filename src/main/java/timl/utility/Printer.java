package timl.utility;

/**
 * Handles output messages to the user
 */
public class Printer {
    protected static final String GREETING = "    ____________________________________________________________\n" +
            "     Hello! I'm TimL\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";
    protected static final String GOODBYE = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";
    protected static final String LIST = "    ____________________________________________________________\n" +
            "     Here's your List DOOD:";
    protected static final String DEFAULT_ERROR = "    ____________________________________________________________\n" +
            "     OOF that's an Invalid message :(\n" +
            "    ____________________________________________________________";
    protected static final String FOUND_NOTHING = "    ____________________________________________________________\n" +
            "     SIR I FOUND NOTHING :(\n" +
            "    ____________________________________________________________";
    protected static final String INVALID_MESSAGE = "    ____________________________________________________________\n" +
            "     OOF that's an Invalid number :(\n" +
            "    ____________________________________________________________";
    protected static final String INVALID_EVENT = "    ____________________________________________________________\n" +
            "     SORRY THAT IS AN INVALID EVENT WITH MISSING PARAMS :(\n" +
            "    ____________________________________________________________";
    protected static final String INVALID_DEADLINE = "    ____________________________________________________________\n" +
            "     SORRY THAT IS AN INVALID DEADLINE WITH MISSING PARAMS :(\n" +
            "    ____________________________________________________________";
    protected static final String FIND_STATEMENT = "    ____________________________________________________________\n" +
            "     HERE ARE YOUR MATCHING TASKS IN YOUR LIST: ";
    protected static final String DELETE_ERROR = "    ____________________________________________________________\n" +
            "     SORRY I CAT DELETE THAT TASK. YOUR PARAMS MUST BE WRONG.\n" +
            "    ____________________________________________________________";
    protected static final String INVALID_TASKOVERFLOW = "    ____________________________________________________________\n" +
            "     SORRY TASK MANAGER HAS FULL CAPACITY :(\n" +
            "    ____________________________________________________________";
    protected static final String EMPTY_CONTENT = "    ____________________________________________________________\n" +
            "     Your task description is empty :-(\n" +
            "    ____________________________________________________________";
    protected static final String IO_EXCEPTION = "    ____________________________________________________________\n" +
            "     IO-exception sir :-(\n" +
            "    ____________________________________________________________";
    protected static final String DELETE_OPENING = "    ____________________________________________________________\n" +
            "     Your task as shown BELOW has sadly been deleted :-(";
    public static void printGreeting() {
        System.out.println(GREETING);
    }

    public static void printGoodbye() {
        System.out.println(GOODBYE);
    }

    public static void printList() {
        System.out.println(LIST);
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
    public static void printMarkedOpening() {
        System.out.println("    ____________________________________________________________\n" +
                "    Nice!! I've marked this task as done:");
    }
    public static void printUnMarkedOpening() {
        System.out.println("    ____________________________________________________________\n" +
                "    Awh :( I've unmarked this task");
    }
    public static void printDefaultError() {
        System.out.println(DEFAULT_ERROR);
    }
    public static void printAddedTaskOpening() {
        System.out.println("    ____________________________________________________________\n" +
                "    Roger that! i've added this task:");
    }
    public static void printInvalidNumber(){
        System.out.println(INVALID_MESSAGE);
    }
    public static void printTaskOverflow(){
        System.out.println(INVALID_TASKOVERFLOW);
    }
    public static void printInvalidEvent(){
        System.out.println(INVALID_EVENT);
    }

    public static void printInvalidDeadline(){
        System.out.println(INVALID_DEADLINE);
    }
    public static void printDeleteOpening(){
        System.out.println(DELETE_OPENING);
    }
    public static void printEmptyTodoCommand(){
        System.out.println(EMPTY_CONTENT);
    }
    public static void printIOException(){
    System.out.println(IO_EXCEPTION);
    }
    public static void printInvalidDelete() {
        System.out.println(DELETE_ERROR);
    }
    public static void printFindOpening() {
        System.out.println(FIND_STATEMENT);
    }
    public static void printFoundNothingMessage(){
        System.out.println(FOUND_NOTHING);
    }
}


