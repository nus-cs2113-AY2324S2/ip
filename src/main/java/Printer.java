public class Printer {
    public static final String H_LINE = "____________________________________________________________\n";
    protected static final String GREET_MESSAGE = H_LINE + "HI I'M JOE\n" + "WHAT CAN I DO FOR YOU\n" + H_LINE;
    protected static final String EXIT_MESSAGE = H_LINE + "GOODBYE. PLEASE COME BACK AGAIN :)\n" + H_LINE;
    protected static final String LIST_MESSAGE = H_LINE + "HERE'S YOUR TASKS:";
    protected static final String DEFAULT_ERROR = H_LINE + "INVALID COMMAND :( PLEASE ENTER SOMETHING I UNDERSTAND\n"
            + H_LINE;
    protected static final String INVALID_MARK_ERROR = H_LINE + "PLEASE ENTER A VALID TASK NUMBER :(\n" + H_LINE;
    protected static final String MARK_MESSAGE = H_LINE + "GOOD JOB BRO. I'VE MARKED IT AS DONE:";
    protected static final String UNMARK_MESSAGE = H_LINE + "OKAY I WILL MARK IT UNDONE:";
    protected static final String TASKS_LIMIT_EXCEEDED_ERROR = H_LINE + "SORRY BRO THERE'S TOO MANY TASKS, I CAN'T " +
            "ADD MORE :(\n" + H_LINE;
    protected static final String TASK_ADDED_MESSAGE = H_LINE + "OKAY I'VE ADDED THIS TASK:\n";
    protected static final String TASKS_NUMBER_MESSAGE = "\nNUMBER OF TASKS CURRENTLY IN LIST: ";
    protected static final String TODO_EMPTY_TASK_ERROR = H_LINE + "??? YOU NEED TO GIVE YOUR TASK A NAME\n" + H_LINE;
    protected static final String DEADLINE_INPUT_ERROR = H_LINE + "INVALID DEADLINE LOL\nPLEASE USE /by TO INDICATE " +
            "DEADLINE TIME\n  SOMETHING LIKE \"--task-- /by --time--\"\n" + H_LINE;
    protected static final String EVENT_INPUT_ERROR = H_LINE + "INVALID EVENT LOL\nPLEASE USE /from AND /to TO " +
            "INDICATE EVENT DURATION\n  SOMETHING LIKE \"--task-- /from --start-- /to --end--\"\n" + H_LINE;

    public static void printHeaderLine() {
        System.out.println(H_LINE);
    }

    public static void printGreeting() {
        System.out.println(GREET_MESSAGE);
    }

    public static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public static void printListMessage() {
        System.out.println(LIST_MESSAGE);
    }

    public static void printDefaultError() {
        System.out.println(DEFAULT_ERROR);
    }

    public static void printInvalidMarkError() {
        System.out.println(INVALID_MARK_ERROR);
    }

    public static void printMarkMessage() {
        System.out.println(MARK_MESSAGE);
    }

    public static void printUnmarkMessage() {
        System.out.println(UNMARK_MESSAGE);
    }

    public static void printTaskOvercapacityError() {
        System.out.println(TASKS_LIMIT_EXCEEDED_ERROR);
    }

    public static void printTaskAddingMessage(String task, int currentNumberOfTasks) {
        System.out.println(TASK_ADDED_MESSAGE + "  " + task + TASKS_NUMBER_MESSAGE + currentNumberOfTasks);
        System.out.println(H_LINE);
    }

    public static void printToDoEmptyError() {
        System.out.println(TODO_EMPTY_TASK_ERROR);
    }

    public static void printDeadlineInputError() {
        System.out.println(DEADLINE_INPUT_ERROR);
    }

    public static void printEventInputError() {
        System.out.println(EVENT_INPUT_ERROR);
    }
}
