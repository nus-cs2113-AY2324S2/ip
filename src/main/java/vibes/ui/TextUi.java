package vibes.ui;

import vibes.common.Messages;
import vibes.task.type.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class TextUi {
    private static final String CHATBOT_NAME = "Vibes";
    public static final String GREETING_MESSAGE = "\t Hello! I'm " + CHATBOT_NAME + "\n\t What can I do for you?";
    public static final String DASHED_LINE = "\t-------------------------------------------------------------------"
            + "--------------------";
    public static final String INDENTED_TAB = "\t   ";
    public static final int TASKS_SIZE_OFFSET = 1;
    public static final String PRINT_TASK_COUNT_FORMAT = "\t Now you have %d tasks in the list.";
    public static final String PRINT_TASK_ITEM_FORMAT = "\t %d. %s";
    public static final int TASK_NUMBER_OFFSET = 1;

    /**
     * Displays the goodbye message.
     */
    public void showByeMessage() {
        System.out.println(Messages.BYE_MESSAGE);
    }

    /**
     * Displays a dashed line.
     */
    public void showLine() {
        System.out.println(DASHED_LINE);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param tasks the list of tasks
     */
    public void showTaskAddedMessage(ArrayList<Task> tasks) {
        System.out.println(Messages.TASK_ADDED_MESSAGE);
        System.out.println(INDENTED_TAB + tasks.get(tasks.size() - TASKS_SIZE_OFFSET));
        System.out.printf((PRINT_TASK_COUNT_FORMAT) + "%n", tasks.size());
    }

    /**
     * Lists all tasks.
     *
     * @param tasks the list of tasks to be listed
     */
    public void listTasks(ArrayList<Task> tasks) {
        System.out.println(Messages.LIST_TASKS_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf((PRINT_TASK_ITEM_FORMAT) + "%n", i + TASK_NUMBER_OFFSET, tasks.get(i));
        }
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param tasks      the list of tasks
     * @param taskNumber the index of the task marked as done
     */
    public void showMarkedMessage(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(Messages.MARKED_DONE_MESSAGE);
        System.out.println(INDENTED_TAB + tasks.get(taskNumber));
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param tasks      the list of tasks
     * @param taskNumber the index of the task marked as not done
     */
    public void showUnmarkedMessage(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(Messages.MARKED_UNDONE_MESSAGE);
        System.out.println(INDENTED_TAB + tasks.get(taskNumber));
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param tasks        the list of tasks
     * @param taskToDelete the task that has been deleted
     */
    public void showDeletedMessage(ArrayList<Task> tasks, Task taskToDelete) {
        System.out.println(Messages.TASK_REMOVED_MESSAGE);
        System.out.println(INDENTED_TAB + taskToDelete);
        System.out.printf((PRINT_TASK_COUNT_FORMAT) + "%n", tasks.size());
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(DASHED_LINE);
        System.out.println(GREETING_MESSAGE);
        System.out.println(DASHED_LINE);
    }

    /**
     * Reads a command from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.out.println(message);
    }

    public void listMatchedTasks(String keyword, ArrayList<Task> tasks) {
        int matchIndex = 1;
        System.out.println(Messages.LIST_MATCHED_TASKS_MESSAGE);
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.printf((PRINT_TASK_ITEM_FORMAT) + "%n", matchIndex, task);
                matchIndex++;
            }
        }
    }
}
