package taskmanager;
import java.util.ArrayList;

/**
 * Collection of methods to print messages
 */

public class Ui {
    protected final static String VERTICAL_LINES = "    ____________________________________________________________";
    protected final static String FIVE_WHITE_SPACES = "     ";

    /**
     * print a vertical line and move to the next line for formatting purposes
     */

    public static void printVerticalLines() {
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Prints "Error" in a format fitting of the program
     */
    public static void printErrorMessage() {
        System.out.println(FIVE_WHITE_SPACES + "Error");
    }

    /**
     * Prints a greeting message personalised with the chat bot's name
     *
     * @param chatBotName The name of the chat bot
     */

    public static void printGreetingMessage(String chatBotName) {
        System.out.println(VERTICAL_LINES + "\n" + FIVE_WHITE_SPACES
                + "Good day my lord! I'm " + chatBotName + "\n"
                + FIVE_WHITE_SPACES + "How shall I serve thee?\n"
                + VERTICAL_LINES +  "\n");
    }

    /**
     * Tells the user goodbye
     */

    public static void printFarewellMessage() {
        System.out.println(VERTICAL_LINES + "\n"
                + FIVE_WHITE_SPACES + "Farewell, my lord.\n"
                + VERTICAL_LINES);
    }

    /**
     * Formatted header for message listing the tasks in the task list
     */

    public static void printStartOfListMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "My lord, here are the tasks as recorded in thy list:");
    }

    /**
     * Prints out a formatted version of the todo task when the todo task is listed out
     *
     * @param itemNum The index the todo task is located at in the task list
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     */

    public static void printTodoListMessage(int itemNum, String type, String status, String description) {
        System.out.println(FIVE_WHITE_SPACES + (itemNum + 1) + "." + "["
                + type + "]" + "[" + status + "] " + description);
    }

    /**
     * Prints out a formatted version of the deadline task when the deadline task is listed out
     *
     * @param itemNum The index the deadline task is located at in the task list
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param deadline When the task have to be completed by
     */

    public static void printDeadlineListMessage(int itemNum, String type, String status,
            String description, String deadline) {
        System.out.println(FIVE_WHITE_SPACES + (itemNum + 1) + "." + "[" + type + "]"
                + "[" + status + "] " + description + " (by: " + deadline + ")");
    }

    /**
     * Prints out a formatted version of the event task when the event task is listed out
     *
     * @param itemNum The index the event task is located at in the task list
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param startDate When the user have to begin completing the task
     * @param deadline When the task have to be completed by
     */

    public static void printEventListMessage(int itemNum, String type, String status,
            String description, String startDate, String deadline) {
        System.out.println(FIVE_WHITE_SPACES + (itemNum + 1) + "." + "[" + type + "]"
                + "[" + status + "] " + description
                + " (from: " + startDate + " to: " + deadline +")");
    }

    /**
     * Tells the user that the task list currently do not contain any task
     */

    public static void printListIsEmptyMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "list is empty");
        System.out.println(VERTICAL_LINES);
    }

    /**
     * Tells the user that the task has been marked as completed or incomplete
     *
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param markedStatus Whether the task is completed or incomplete
     */

    public static void printMarkOrUnmarkTaskMessage(String type, String status, String description,
            String markedStatus) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Acknowledged sire, I've marked this task as " + markedStatus + ":");
        System.out.println(FIVE_WHITE_SPACES
                + "  " + "[" + type + "]"
                + "[" + status + "] "
                + description);
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have successfully added a todo task to the task list
     *
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param taskCounter Counter to keep track of the number of task in the task list
     */
    public static void printAddTodoMessage(String type, String status, String description, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Got it sire. I've added this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description);
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + taskCounter + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have successfully added a deadline task to the task list
     *
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param endDate When the task have to be completed by
     * @param taskCounter Counter to keep track of the number of task in the task list
     */

    public static void printAddDeadlineMessage(String type, String status, String description,
            String endDate, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Got it sire. I've added this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description +
                " (by: " + endDate + ")");
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have successfully added a event task to the task list
     *
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param startDate When the user have to begin completing the task
     * @param endDate When the task have to be completed by
     * @param taskCounter Counter to keep track of the number of task in the task list
     */

    public static void printAddEventMessage(String type, String status, String description,
            String startDate, String endDate, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Got it sire. I've added this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description +
                " (from: " + startDate + " to: " + endDate +
                ")");
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have made a typo in their command and the format their commands should be in
     */

    public static void printTypoErrorMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Sorry my lord, I believe that you have made a typo");
        System.out.println(FIVE_WHITE_SPACES + "Remember the format for messages are:");
        System.out.println(FIVE_WHITE_SPACES + "1) Todo: 'todo (Task)'");
        System.out.println(FIVE_WHITE_SPACES + "2) Deadline: 'deadline (Task) /by (due date)'");
        System.out.println(FIVE_WHITE_SPACES + "3) Event: 'event (Task) /from (start date) /to (end date)'");
        System.out.println(FIVE_WHITE_SPACES + "4) List: 'list'");
        System.out.println(FIVE_WHITE_SPACES + "5) Delete: 'delete (task)'");
        System.out.println(FIVE_WHITE_SPACES + "6) Find: 'find (task)'");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have made a typo when inputting the task type and how they can rectify their mistake
     */

    public static void printInvalidTaskTypeMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Invalid Task Type sire, tasks can only be todo, deadline or event");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have made a typo when deleting a task and how they can rectify their mistake
     */

    public static void printInvalidDeleteIndexMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES +
                "Check if you have entered a number greater than the size of the task list.");
        System.out.println(FIVE_WHITE_SPACES +
                "Or perhaps you may not have inputted a number");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have made a typo when accessing a task attribute and how they can rectify their mistake
     */

    public static void printInvalidTaskAttributeMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Invalid Task Attribute  sire. " +
                "Did you forget to set this attribute?");
        System.out.println(FIVE_WHITE_SPACES + "You get to pass for now but go back and set the attribute");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have successfully deleted a todo task from the task list
     *
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param taskCounter Counter to keep track of the number of task in the task list
     */

    public static void printDeleteTodoMessage(String type, String status, String description, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Noted sire. I've removed this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description);
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + taskCounter + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have successfully deleted a deadline task from the task list
     *
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param endDate When the task have to be completed by
     * @param taskCounter Counter to keep track of the number of task in the task list
     */

    public static void printDeleteDeadlineMessage(String type, String status, String description,
            String endDate, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Noted sire. I've removed this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description +
                " (by: " + endDate + ")");
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have successfully deleted an event task from the task list
     *
     * @param type Whether the task is a todo, deadline or event
     * @param status Whether the task is completed or not
     * @param description What the task is about
     * @param startDate When the user have to begin completing the task
     * @param endDate When the task have to be completed by
     * @param taskCounter Counter to keep track of the number of task in the task list
     */

    public static void printDeleteEventMessage(String type, String status, String description,
            String startDate, String endDate, int taskCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Noted sire. I've removed this task:");
        System.out.println(FIVE_WHITE_SPACES + "  " + "[" + type + "]" +
                "[" + status + "] " + description +
                " (from: " + startDate + " to: " + endDate +
                ")");
        System.out.println(FIVE_WHITE_SPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
        System.out.println(VERTICAL_LINES + "\n");
    }
    public static void printFindMessage(ArrayList<Task> temporaryArray, int temporaryArrayCounter) {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Sire here are the task I have found as per thy request");
        for(int iterator = 0; iterator < temporaryArrayCounter; iterator += 1) {
            if (temporaryArray.get(iterator).getTaskType().equals("T")) {
                todoListMessage(iterator, temporaryArray.get(iterator).getTaskType(),
                        temporaryArray.get(iterator).getStatusIcon(),
                        temporaryArray.get(iterator).getDescription());
            } else if (temporaryArray.get(iterator).getTaskType().equals("D")) {
                deadlineListMessage(iterator, temporaryArray.get(iterator).getTaskType(),
                        temporaryArray.get(iterator).getStatusIcon(),
                        temporaryArray.get(iterator).getDescription(),
                        temporaryArray.get(iterator).getEndDate());
            } else {
                eventListMessage(iterator, temporaryArray.get(iterator).getTaskType(),
                        temporaryArray.get(iterator).getStatusIcon(),
                        temporaryArray.get(iterator).getDescription(),
                        temporaryArray.get(iterator).getStartDate(),
                        temporaryArray.get(iterator).getEndDate());
            }
        }
        System.out.println(VERTICAL_LINES + "\n");
    }

    /**
     * Tells the user that they have made a typo when marking or unmarking a task and how they can rectify their mistake
     */

    public static void printMarkOrUnmarkTaskErrorMessage() {
        System.out.println(VERTICAL_LINES);
        System.out.println(FIVE_WHITE_SPACES + "Sire the task you have asked me to mark or unmark does not exist.");
        System.out.println(FIVE_WHITE_SPACES + "Maybe you have written a number that do not have a task that " +
                "corresponds to it.");
        System.out.println(FIVE_WHITE_SPACES + "Or you did not write a number at all.");
        System.out.println(VERTICAL_LINES);
    }

}
