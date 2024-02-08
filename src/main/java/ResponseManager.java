public class ResponseManager {
    private static final String LOGO =
            "███████╗██╗   ██╗██╗  ██╗███████╗\n" +
            "╚══███╔╝██║   ██║██║ ██╔╝██╔════╝\n" +
            "  ███╔╝ ██║   ██║█████╔╝ █████╗  \n" +
            " ███╔╝  ██║   ██║██╔═██╗ ██╔══╝  \n" +
            "███████╗╚██████╔╝██║  ██╗███████╗\n" +
            "╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
    private static final String INDENTATION_LINE =
            "____________________________________________________________";
    private static final String BYE_MESSAGE =
            "Bye. Hope to see you again soon!\n";
    private static final String TASK_ADDED_MESSAGE =
            "Got it. I've added this task:\n";
    private static final String MARK_ERROR_MESSAGE =
            "please enter a valid index for mark!\n";
    private static final String UNMARK_ERROR_MESSAGE =
            "please enter a valid index for unmark!\n";
    private static final String STANDARD_ERROR_MESSAGE =
            "sorry, I don't get your words :(\n";
    private static final String LIST_TASK_MESSAGE =
            "Here are the tasks in your list:\n";
    private static final String UNMARKED_MESSAGE =
            "OK, I've marked this task as not done yet:\n";
    private static final String MARKED_MESSAGE =
            "Nice! I've marked this task as done:\n";
    private static final String END_LINE = System.lineSeparator();
    public static void indentPrint(String response) {
        System.out.println(INDENTATION_LINE);
        System.out.println(response + INDENTATION_LINE);
    }
    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
        indentPrint("Hello! I'm Zuke\n" +
                "What can I do for you?\n");
    }

    public static void sayGoodbye() {
        indentPrint(BYE_MESSAGE);
    }

    public static void listTaskToUser(String taskList) {
        indentPrint(LIST_TASK_MESSAGE + taskList);
    }

    public static void sendTaskAddedToUser(String messageToPrint) {
        indentPrint(TASK_ADDED_MESSAGE + " " +
                messageToPrint + END_LINE);
    }

    public static void printMarkOrUnMarkTask(String action, String task) {
        switch(action) {
        case "mark":
            indentPrint(MARKED_MESSAGE + " " + task + END_LINE);
            break;

        case "unmark":
            indentPrint(UNMARKED_MESSAGE + " " + task + END_LINE);
            break;

        default:
            break;
        }

    }

    public static void printErrorMessage(String errorType) {
        switch(errorType) {
        case "mark":
            indentPrint(MARK_ERROR_MESSAGE);
            break;

        case "unmark":
            indentPrint(UNMARK_ERROR_MESSAGE);
            break;

        default:
            indentPrint(STANDARD_ERROR_MESSAGE);
            break;
        }
    }
}
