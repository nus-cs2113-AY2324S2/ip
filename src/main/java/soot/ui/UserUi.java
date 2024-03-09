package soot.ui;

import soot.task.Task;
import soot.task.TaskList;

import java.util.ArrayList;

/**
 * Class UserUi handles interactions with the user.
 * This includes display of user interfaces, such as displaying the divider line.
 */
public class UserUi {
    private static final String INDENT = "  ";
    private static final int DIVIDER_LENGTH = 60;


    /**
     * Prints a greeting to the user when the chatbot is first run.
     * Chatbot will introduce itself and question for a user input.
     */
    public static void showUserGreeting() {
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        displayDividerLine();
    }

    /**
     * Prints a goodbye greeting to the user when the user stops the chatbot.
     */
    public static void showGoodbyeMessage() {
        System.out.println("Bye! Till the next time we meet... \n" +
                "i've saved your list for future use <3");
        displayDividerLine();
    }

    /**
     * Prints a horizontal line that acts as a divider within the conversation between the
     * user input and the chatbot.
     * Divider Line will be displayed after the user input before the chatbot speaks.
     */
    public static void displayDividerLine() {
        for (int i = 0; i < DIVIDER_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    /**
     * Prints a 2 space indent for a pleasant UI experience.
     */
    public static void displayIndent() {
        System.out.print(INDENT);
    }

    /**
     * Prints the size of the TaskList to indicate the number of tasks in the list,
     * in the required format.
     */
    public static void printTaskCount() {
        int taskCount = TaskList.getSize();
        System.out.println("you currently have a total of " + taskCount + " tasks in your list :)");
    }

    /**
     * Prints the list of tasks that contains the keyword specified by the user.
     *
     * @param foundKeywordList ArrayList of tasks that contain the keyword.
     */
    public static void printKeywordList(ArrayList<Task> foundKeywordList) {
        if (foundKeywordList.isEmpty()) {
            System.out.println("i couldn't find any tasks with this word.");
        } else {
            System.out.println("i found your word in these tasks:");
            for (int i = 0; i < foundKeywordList.size(); i++) {
                UserUi.displayIndent();
                Task currentTask = foundKeywordList.get(i);
                currentTask.printTaskInListFormat(i + 1);
            }
        }
        UserUi.displayDividerLine();
    }

    /**
     * Prints a provided message to the terminal for the user, followed by a divider line.
     *
     * @param messageToPrint string containing the message to be printed to terminal.
     */
    public static void printMessageWithDivider(String messageToPrint) {
        System.out.println(messageToPrint);
        UserUi.displayDividerLine();
    }

    /**
     * Prints to user how many tasks they have left to do.
     * Method is called only when "done" or "unmark" is called.
     */
    public static void printUndoneTasksCount() {
        int undoneCounter = TaskList.getSize() - TaskList.getCountDoneTasks();
        System.out.println("you now have " + undoneCounter + " tasks left to do!");
    }

    public static void printAllCommands() {
        System.out.println("here's all the commands i recognise: ");
        System.out.println("    ~~~    ");
        System.out.println("to add a task, remember to specify the task type");
        System.out.println("1. todo TASK_NAME");
        System.out.println("2. deadline TASK_NAME /by DUE_DATE");
        System.out.println("3. event TASK_NAME /from START_DATE /to END_DATE");

        System.out.println("    ~~~    ");
        System.out.println("to modify ur task list: ");
        System.out.println("- list (to view you list)");
        System.out.println("- done TASK_INDEX_IN_LIST (mark a task as done)");
        System.out.println("- unmark TASK_INDEX_IN_LIST (mark a task as undone)");
        System.out.println("- delete TASK_INDEX_IN_LIST (delete a task from your list)");
        System.out.println("- find KEYWORD_TO_FIND (find a specific word in your task list)");
        System.out.println("- help (to view all valid commands i recognise");

        System.out.println("    ~~~    ");
        System.out.println("still lost? don't worry, visit this link for more info");
        System.out.println("    https://claribelho.github.io/ip/");
        UserUi.displayDividerLine();
    }
}
