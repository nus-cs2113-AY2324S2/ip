package taskmanager;

public class Messages {
    protected final static String VERTICAL_LINES = "    ____________________________________________________________";
    protected final static String FIVE_WHITE_SPACES = "     ";

    public static void printVerticalLines() {
        System.out.println(VERTICAL_LINES + "\n");
    }
    public static void errorMessage() {
        System.out.println(FIVE_WHITE_SPACES + "Error");
    }

    public static void printGreetingMessage(String chatBotName) {
        System.out.println(VERTICAL_LINES + "\n" + FIVE_WHITE_SPACES
                + "Good day my lord! I'm " + chatBotName + "\n"
                + FIVE_WHITE_SPACES + "How shall I serve thee?\n"
                + VERTICAL_LINES +  "\n");
    }
    public static void printFarewellMessage() {
        System.out.println(VERTICAL_LINES + "\n"
                + FIVE_WHITE_SPACES + "Farewell, my lord.\n"
                + VERTICAL_LINES);
    }
    public static void startOfListMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "My lord, here are the tasks as recorded in thy list:");
    }
    public static void todoListMessage(int itemNum, String type, String status, String description) {
        System.out.println(FIVE_WHITE_SPACES + (itemNum + 1) + "." + "["
                + type + "]" + "[" + status + "] " + description);
    }
    public static void deadlineListMessage(int itemNum, String type, String status,
            String description, String deadline) {
        System.out.println(FIVE_WHITE_SPACES + (itemNum + 1) + "." + "[" + type + "]"
                + "[" + status + "] " + description + " (by: " + deadline + ")");
    }
    public static void eventListMessage(int itemNum, String type, String status,
            String description, String startDate, String deadline) {
        System.out.println(FIVE_WHITE_SPACES + (itemNum + 1) + "." + "[" + type + "]"
                + "[" + status + "] " + description
                + " (from: " + startDate + " to: " + deadline +")");
    }
    public static void listIsEmptyMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "list is empty");
        System.out.println(VERTICAL_LINES);
    }

    public static void markOrUnmarkTaskMessage(String type, String status, String description,
            String markedStatus) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Acknowledged sire, I've marked this task as " + markedStatus + ":");
        System.out.println(FIVE_WHITE_SPACES
                + "  " + "[" + type + "]"
                + "[" + status + "] "
                + description);
        System.out.println(VERTICAL_LINES + "\n");
    }
    public static void addTodoMessage(String type, String status, String description, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Got it. I've added this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description);
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + taskCounter + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }

    public static void addDeadlineMessage(String type, String status, String description,
            String endDate, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Got it. I've added this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description +
                " (by: " + endDate + ")");
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }
    public static void addEventMessage(String type, String status, String description,
                                          String startDate, String endDate, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Got it. I've added this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description +
                " (from: " + startDate + " to: " + endDate +
                ")");
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }
    public static void typoErrorMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Sorry my lord, I believe that you have made a typo");
        System.out.println(FIVE_WHITE_SPACES + "Remember the format for messages are:");
        System.out.println(FIVE_WHITE_SPACES + "1) Todo: 'todo (taskmanager.Task)'");
        System.out.println(FIVE_WHITE_SPACES + "2) Deadline: 'deadline (taskmanager.Task) /by (due date)'");
        System.out.println(FIVE_WHITE_SPACES + "3) Event: 'event (taskmanager.Task) /from (start date) /to (end date)'");
        System.out.println(FIVE_WHITE_SPACES + "4) List: 'list'");
        System.out.println(VERTICAL_LINES + "\n");
    }

    public static void invalidTaskTypeMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Invalid taskmanager.Task Type sire, tasks can only be todo, deadline or event");
        System.out.println(VERTICAL_LINES + "\n");
    }
    public static void invalidTaskAttributeMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Invalid taskmanager.Task Attribute  sire. Did you forget to set this attribute?");
        System.out.println(FIVE_WHITE_SPACES + "You get to pass for now but go back and set the attribute");
        System.out.println(VERTICAL_LINES + "\n");
    }
}
