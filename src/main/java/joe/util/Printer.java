package joe.util;

/**
 * Handles output messages to the user on the command line
 */
public class Printer {
    protected static final String H_LINE = "____________________________________________________________\n";
    protected static final String GREET_MESSAGE = H_LINE + "HI I'M JOE\n" + "WHAT CAN I DO FOR YOU\n" + H_LINE;
    protected static final String EXIT_MESSAGE = H_LINE + "GOODBYE. PLEASE COME BACK AGAIN :)\n" + H_LINE;
    protected static final String LIST_MESSAGE = H_LINE + "HERE'S YOUR TASKS:";
    protected static final String DEFAULT_ERROR = H_LINE + "INVALID COMMAND :( PLEASE ENTER SOMETHING I UNDERSTAND\n"
            + H_LINE + "YOU CAN USE THESE COMMANDS:\n\nbye\n\tEXIT JOE"
            + "\n\nlist\n\tLIST ALL YOUR TASKS THAT I'VE RECORDED"
            + "\n\ntodo --TASK--\ndeadline --TASK-- /by --TIME--\nevent --TASK-- /from --START TIME-- /to --END TIME--"
            + "\n\tADD A TODO/DEADLINE/EVENT TASK\n\tINPUT DATE TIME IN THE FORMAT: dd-mm-yyyy HHmm"
            + "\n\nmark --TASK NUMBER--\nunmark --TASK NUMBER--\n\tMARK/UNMARK YOUR TASK (NUMBER IN LIST) DONE"
            + "\n\ndelete --TASK NUMBER--\n\tDELETE A TASK (NUMBER IN LIST) FROM THE LIST"
            + "\n\nfind --KEYWORD--\n\tFIND ALL TASKS CONTAINING A KEYWORD\n"
            + H_LINE;
    protected static final String EXIT_INPUT_ERROR = H_LINE + "SORRY I DON'T UNDERSTAND :( DID YOU MEAN\n\tbye\n"
            + H_LINE;
    protected static final String LIST_INPUT_ERROR = H_LINE + "SORRY I DON'T UNDERSTAND :( DID YOU MEAN\n\tlist\n"
            + H_LINE;
    protected static final String INVALID_MARK_ERROR = H_LINE + "PLEASE ENTER A VALID TASK NUMBER :(\n"
            + "\tUSE: \"mark --INTEGER--\" OR \"unmark --INTEGER--\"\n\tYOU CAN list FOR REFERENCE\n" + H_LINE;
    protected static final String MARK_MESSAGE = H_LINE + "GOOD JOB BRO. I'VE MARKED IT AS DONE:";
    protected static final String UNMARK_MESSAGE = H_LINE + "OKAY I WILL MARK IT UNDONE:";
    protected static final String TASK_ADDED_MESSAGE = H_LINE + "OKAY I'VE ADDED THIS TASK:\n";
    protected static final String TASKS_NUMBER_MESSAGE = "NUMBER OF TASKS CURRENTLY IN LIST: ";
    protected static final String TODO_EMPTY_TASK_ERROR = H_LINE + "??? YOU NEED TO GIVE YOUR TASK A NAME\n"
            + "\ttodo --TASK--\n" + H_LINE;
    protected static final String DEADLINE_INPUT_ERROR = H_LINE + "INVALID DEADLINE LOL\nPLEASE USE /by TO INDICATE "
            + "DEADLINE TIME\n\tdeadline --TASK-- /by --DATE TIME--\n\tINPUT DATE TIME IN THE FORMAT: dd-mm-yyyy HHmm\n"
            + H_LINE;
    protected static final String EVENT_INPUT_ERROR = H_LINE + "INVALID EVENT LOL\nPLEASE USE /from AND /to TO "
            + "INDICATE EVENT DURATION\n\tevent --TASK-- /from --START DATE TIME-- /to --END DATE TIME--\n"
            + "\tINPUT DATE TIME IN THE FORMAT: dd-mm-yyyy HHmm\n" + H_LINE;
    protected static final String DELETE_MESSAGE = H_LINE + "COOL BEANS I WILL REMOVE THAT TASK:";
    protected static final String DELETE_ERROR = H_LINE + "SORRY BUT I CAN'T DELETE WHATEVER YOU'RE REFERRING TO BRO\n"
            + "\tUSE A VALID INTEGER PLEASE: \"delete --INTEGER--\"\n" + H_LINE;
    protected static final String FILE_CORRUPT_ERROR = H_LINE + "OOPS LOOKS LIKE THERE WERE CORRUPTED LINES OF CODE\n"
            + "\tNUMBER OF CORRUPTED LINES: ";
    protected static final String LOAD_ERROR = H_LINE + "SORRY I COULDN'T GET YOUR PREVIOUS DATA"
            + "\n\t maybe it got corrupted lol\n" + H_LINE;
    protected static final String SAVE_ERROR = H_LINE + "SORRY I CURRENTLY CAN'T SAVE YOUR LIST MY BAD LOL\n" + H_LINE;
    protected static final String FIND_LIST_MESSAGE = H_LINE + "HERE ARE ALL TASKS WITH KEYWORD: ";
    protected static final String FIND_NO_MATCH_MESSAGE = "SORRY MAN THERE'S NO TASK WITH THAT KEYWORD";
    protected static final String FIND_ERROR = H_LINE + "YOU HAVE TO ENTER A WORD FOR ME TO FIND IT BRO\n\t"
            + "USE A WORD PLEASE: \"find --KEYWORD--\"\n" + H_LINE;

    /**
     * Prints a header line onto the output console
     */
    public static void printHeaderLine() {
        System.out.println(H_LINE);
    }

    /**
     * Prints a greeting message onto the output console
     */
    public static void printGreeting() {
        System.out.println(GREET_MESSAGE);
    }

    /**
     * Prints an exit message onto the output console
     */
    public static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Prints a message onto the output console for when the list command is executed
     */
    public static void printListMessage() {
        System.out.println(LIST_MESSAGE);
    }

    /**
     * Prints an error message onto the console for when there is an error in exiting the program
     */
    public static void printExitError() {
        System.out.println(EXIT_INPUT_ERROR);
    }

    /**
     * Prints an error message onto the console for when there is an error executing the list command
     */
    public static void printListError() {
        System.out.println(LIST_INPUT_ERROR);
    }

    /**
     * Prints an error message onto the console for when there is an invalid command word input by the user
     */
    public static void printDefaultError() {
        System.out.println(DEFAULT_ERROR);
    }

    /**
     * Prints an error message onto the console for when there is an error executing the mark or unmark command
     */
    public static void printInvalidMarkError() {
        System.out.println(INVALID_MARK_ERROR);
    }

    /**
     * Prints a message onto the console for when the mark command is executed
     */
    public static void printMarkMessage() {
        System.out.println(MARK_MESSAGE);
    }

    /**
     * Prints a message onto the console for when the unmark command is executed
     */
    public static void printUnmarkMessage() {
        System.out.println(UNMARK_MESSAGE);
    }

    /**
     * Prints a message containing the number of tasks currently in the task list onto the console
     *
     * @param numOfTasks a non-negative integer containing the number of tasks in the task list
     */
    public static void printNumOfTasks(int numOfTasks) {
        System.out.println(TASKS_NUMBER_MESSAGE + numOfTasks);
        System.out.println(H_LINE);
    }

    /**
     * Prints a message for when a task is newly added to the task list
     *
     * @param task a String of the task description to print
     * @param numOfTasks a non-negative integer containing the number of tasks in the task list
     */
    public static void printTaskAddingMessage(String task, int numOfTasks) {
        System.out.println(TASK_ADDED_MESSAGE + "  " + task);
        printNumOfTasks(numOfTasks);
    }

    /**
     * Prints an error message onto the console for when there is an empty string input as a todo by the user
     */
    public static void printToDoEmptyError() {
        System.out.println(TODO_EMPTY_TASK_ERROR);
    }

    /**
     * Prints an error message onto the console for when there is an invalid input in the user's deadline command input
     */
    public static void printDeadlineInputError() {
        System.out.println(DEADLINE_INPUT_ERROR);
    }

    /**
     * Prints an error message onto the console for when there is an invalid input in the user's event command input
     */
    public static void printEventInputError() {
        System.out.println(EVENT_INPUT_ERROR);
    }

    /**
     * Prints a message for when the delete command is executed
     */
    public static void printDeleteMessage() {
        System.out.println(DELETE_MESSAGE);
    }

    /**
     * Prints an error message onto the console for when there is an error executing the delete command
     */
    public static void printDeleteError() {
        System.out.println(DELETE_ERROR);
    }

    /**
     * Prints an error message onto the console for when there is an error reading data from the save text file and
     * prints the number of lines in the text file that were corrupted
     *
     * @param numOfLines a positive integer indicating the number of corrupted lines in the file
     */
    public static void printCorruptedFileError(int numOfLines) {
        System.out.println(FILE_CORRUPT_ERROR + numOfLines);
        printHeaderLine();
    }

    /**
     * Prints an error message onto the console for when there is an error in saving the task list into a text file
     */
    public static void printSaveError() {
        System.out.println(SAVE_ERROR);
    }

    /**
     * Prints an error message onto the console for when there is an error loading and reading data from a text file
     */
    public static void printLoadError() {
        System.out.println(LOAD_ERROR);
    }

    /**
     * Prints a message for when the find command is executed
     *
     * @param keyword a String of the keyword to be found in the find command
     */
    public static void printFindMessage(String keyword) {
        System.out.println(FIND_LIST_MESSAGE + keyword);
    }

    /**
     * Prints a message for when the find command is executed and no matching keyword is found among all tasks
     * in the task list
     */
    public static void printNoMatchMessage() {
        System.out.println(FIND_NO_MATCH_MESSAGE);
    }

    /**
     * Prints an error message onto the console for when there is an error executing the find command
     */
    public static void printFindError() {
        System.out.println(FIND_ERROR);
    }
}
