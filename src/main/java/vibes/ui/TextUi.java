package vibes.ui;

import vibes.common.Messages;
import vibes.task.type.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    private static final String CHATBOT_NAME = "Vibes";
    public static final String GREETING_MESSAGE = "\t Hello! I'm " + CHATBOT_NAME + "\n\t What can I do for you?";
    public static final String DASHED_LINE = "\t---------------------------------------------------------------------------------------";
    public static final String INDENTED_TAB = "\t   ";
    public static final int TASKS_SIZE_OFFSET = 1;
    public static final String PRINT_TASK_COUNT_FORMAT = "\t Now you have %d tasks in the list.";
    public static final String PRINT_TASK_ITEM_FORMAT = "\t %d. %s";
    public static final int TASK_NUMBER_OFFSET = 1;


    public void showByeMessage() {
        System.out.println(Messages.BYE_MESSAGE);
    }

    public void showLine() {
        System.out.println(DASHED_LINE);
    }

    public void showTaskAddedMessage(ArrayList<Task> tasks) {
        System.out.println(Messages.TASK_ADDED_MESSAGE);
        System.out.println(INDENTED_TAB + tasks.get(tasks.size() - TASKS_SIZE_OFFSET));
        System.out.printf((PRINT_TASK_COUNT_FORMAT) + "%n", tasks.size());
    }

    public void listTasks(ArrayList<Task> tasks) {
        System.out.println(Messages.LIST_TASKS_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf((PRINT_TASK_ITEM_FORMAT) + "%n", i + TASK_NUMBER_OFFSET, tasks.get(i));
        }
    }

    public void showMarkedMessage(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(Messages.MARKED_DONE_MESSAGE);
        System.out.println(INDENTED_TAB + tasks.get(taskNumber));
    }

    public void showUnmarkedMessage(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(Messages.MARKED_UNDONE_MESSAGE);
        System.out.println(INDENTED_TAB + tasks.get(taskNumber));
    }

    public void showDeletedMessage(ArrayList<Task> tasks, Task taskToDelete) {
        System.out.println(Messages.TASK_REMOVED_MESSAGE);
        System.out.println(INDENTED_TAB + taskToDelete);
        System.out.printf((PRINT_TASK_COUNT_FORMAT) + "%n", tasks.size());
    }

    public void showWelcomeMessage() {
        System.out.println(DASHED_LINE);
        System.out.println(GREETING_MESSAGE);
        System.out.println(DASHED_LINE);
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
