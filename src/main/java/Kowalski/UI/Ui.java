package Kowalski.UI;

public class Ui {
    private static final String DIVIDING_LINE = "____________________________________________________________";
    private static final String KOWALSKI_INTRODUCTION = "Welcome Skipper! I'm Kowalski, reporting for Duty!";
    private static final String KOWALSKI_STARTING_QN = "What can I do for you today?";
    private static final String KOWALSKI_ECHO = "Enter commands, and I will echo them back to you, " +
            "as well as add them to your list.";
    private static final String KOWALSKI_LIST_COMMAND = "Type 'list' to see your to-do list.";
    private static final String KOWALSKI_MARK_COMMAND = "Type 'mark' to mark a task as done.";
    private static final String KOWALSKI_UNMARK_COMMAND = "Type 'unmark' to mark a task as not done.";
    private static final String KOWALSKI_TODO_TASK_COMMAND = "Type 'todo <description>' to add a task to the list.";
    private static final String KOWALSKI_DEADLINE_TASK_COMMAND = "Type 'deadline <description> /by <deadline>' to add a task " +
            "with a deadline to the list.";
    private static final String KOWALSKI_EVENT_TASK_COMMAND = "Type 'event <description> /from <start> /to <end>' to add an " +
            "event to the list.";
    private static final String KOWALSKI_DELETE_TASK_COMMAND = "Type 'delete <index>' to delete a task from your list.";
    private static final String KOWALSKI_BYE_COMMAND = "Type 'bye' to end the conversation.";
    private static final String ZERO_TASK_MESSAGE = "Now you have 0 tasks in the list.";
    private static final String ONE_TASK_MESSAGE = "Now you have 1 task in the list.";
    private static final String KOWALSKI_BYE_MESSAGE = "Bye Skipper! Hope to serve you again for your next mission!";

    /**
     * Prints out the message introducing the functionalities of Kowalski Bot
     */
    public static void printIntro(){
        System.out.println(DIVIDING_LINE);
        System.out.println(KOWALSKI_INTRODUCTION + System.lineSeparator());
        printHelpCommands();
        System.out.println(System.lineSeparator() + KOWALSKI_STARTING_QN );
        printDivider();
    }

    /**
     * Prints out a list of commands to guide the user to accurately use the Kowalski Bot
     */
    public static void printHelpCommands(){
        System.out.println(KOWALSKI_ECHO);
        System.out.println(KOWALSKI_LIST_COMMAND);
        System.out.println(KOWALSKI_MARK_COMMAND);
        System.out.println(KOWALSKI_UNMARK_COMMAND);
        System.out.println(KOWALSKI_TODO_TASK_COMMAND);
        System.out.println(KOWALSKI_DEADLINE_TASK_COMMAND);
        System.out.println(KOWALSKI_EVENT_TASK_COMMAND);
        System.out.println(KOWALSKI_DELETE_TASK_COMMAND);
        System.out.println(KOWALSKI_BYE_COMMAND);
    }

    /**
     * Prints a dividing line between statements for added clarity
     */
    public static void printDivider(){
        System.out.println(DIVIDING_LINE);
    }

    /**
     * Prints out an accurate message for the number of tasks in the list.
     * @param numberOfTasks : represents the total current task count
     */
    public static void printCurrentTaskMessage(int numberOfTasks){
        switch (numberOfTasks){
        case 0:
            System.out.println(ZERO_TASK_MESSAGE);
            break;
        case 1:
            System.out.println(ONE_TASK_MESSAGE);
            break;
        default:
            System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        }
    }

    /**
     * Prints out the message to end conversation with the user
     */
    public static void printEndConversation(){
        System.out.println(KOWALSKI_BYE_MESSAGE);
        printDivider();
    }
}
