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
            "Bye. Hope to see you again soon!";
    private static final String TASK_ADDED_MESSAGE =
            "Got it. I've added this task:\n";
    private static final String MARK_ERROR_MESSAGE =
            "please enter a valid index for mark!";
    private static final String UNMARK_ERROR_MESSAGE =
            "please enter a valid index for unmark!";
    private static final String STANDARD_ERROR_MESSAGE =
            "sorry, I don't get your words :(";

    public static void indentPrint(String response) {
        System.out.println(INDENTATION_LINE);
        System.out.println(response);
        System.out.println(INDENTATION_LINE);
    }
    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
        indentPrint("Hello! I'm Zuke\n" +
                "What can I do for you?");
    }

    public static void sayGoodbye() {
        indentPrint(BYE_MESSAGE);
    }

    public static void listTaskToUser(TaskList taskList) {
        taskList.listTasks();
    }

    public static void sendTaskAddedToUser(TaskList taskList) {
        indentPrint(TASK_ADDED_MESSAGE + " " +
                taskList.showNewlyAddedTask() + "\n" +
                taskList);
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
