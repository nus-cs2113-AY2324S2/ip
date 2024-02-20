package tool;
public class ResponseManager {
    private static final String LOGO =
            "███████╗██╗   ██╗██╗  ██╗███████╗\n" +
            "╚══███╔╝██║   ██║██║ ██╔╝██╔════╝\n" +
            "  ███╔╝ ██║   ██║█████╔╝ █████╗  \n" +
            " ███╔╝  ██║   ██║██╔═██╗ ██╔══╝  \n" +
            "███████╗╚██████╔╝██║  ██╗███████╗\n" +
            "╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
    public static final String TODO = "todo\n";
    public static final String DEADLINE = "deadline\n";
    public static final String EVENT = "event\n";
    private static final String INDENTATION_LINE =
            "____________________________________________________________";
    private static final String BYE_MESSAGE =
            "Bye. Hope to see you again soon!\n";
    private static final String TASK_ADDED_MESSAGE =
            "Got it. I've added this task:\n";
    public static final String INDEX_ERROR_MESSAGE =
            "please enter a valid index!\n";
    public static final String COMMAND_ERROR =
            "please enter a valid command\n";
    public static final String FORMAT_ERROR_MESSAGE =
            "please follow the valid format for ";
    public static final String BLANK_MSG_ERROR =
            "make sure the required field is not empty.\n hit /h for help\n";
    private static final String LIST_TASK_MESSAGE =
            "Here are the tasks in your list:\n";
    private static final String UNMARKED_MESSAGE =
            "OK, I've marked this task as not done yet:\n";
    private static final String MARKED_MESSAGE =
            "Nice! I've marked this task as done:\n";
    private static final String END_LINE = System.lineSeparator();
    private static final String DELETE_MESSAGE =
            "Noted. I've removed the task:\n";

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

    public static void printActionOnTasks(String action, String msgTobePrinted) {
        switch(action) {
        case "mark":
            indentPrint(MARKED_MESSAGE + " " + msgTobePrinted);
            break;

        case "unmark":
            indentPrint(UNMARKED_MESSAGE + " " + msgTobePrinted);
            break;

        case "delete":
            indentPrint(DELETE_MESSAGE + " " + msgTobePrinted);

        default:
            break;
        }

    }
}
