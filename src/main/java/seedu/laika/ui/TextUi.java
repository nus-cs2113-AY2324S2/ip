package seedu.laika.ui;

import seedu.laika.task.Task;
import seedu.laika.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {

    public static final String PREFIX = "Laika: ";

    public static final String LOGO = " ^..^      /\n"
                                    + " /_/\\_____/\n"
                                    + "    /\\   /\\\n"
                                    + "   /  \\ /  \\\n";

    public static final String MESSAGE_NO_SAVE_DATA = "No save data detected, creating new file!";

    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "You dont have so many tasks!";

    public static final String MESSAGE_INVALID_COMMAND_WORD = "Your command word is wrong!";

    public static final String MESSAGE_TODO_FORMAT = "Remember to input your todo task in the format \"todo TASK\"";

    public static final String MESSAGE_DEADLINE_FORMAT = "Remember to input your task with a deadline in the format \"deadline TASK /by BY\"!";

    public static final String MESSAGE_EVENT_FORMAT = "Remember to input your event task in the format \"event TASK /from FROM /to TO\"!";

    public static final String MESSAGE_EMPTY_LIST = "You have no tasks in your task list!";

    public TextUi() {
    }

    public static void showNumberOfTasksLeft(TaskList taskList) {
        System.out.println(PREFIX + "You have " + taskList.getSize() + " tasks left!");
    }

    public static void startMessage() {
        System.out.println(PREFIX + "Hi! My name is Laika!\n\n" + LOGO + PREFIX + "How can I help you?");
    }

    public static String getUserCommand() {
        System.out.print("Command: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void markAsDoneMessage() {
        System.out.println(PREFIX + "Good job! Task has been marked as done.");
    }

    public static void markAsUnDoneMessage() {
        System.out.println(PREFIX + "Alright, task has been marked as undone.");
    }

    public static void deleteTaskMessage(ArrayList<Task> tasks, int taskNumber) {
        System.out.println("Laika: Gotcha! I've dealt with this task:" + System.lineSeparator()
                + tasks.get(taskNumber-1));
    }

    public static void showErrorMessage(String message) {
        System.out.println(PREFIX + message);
    }

    public static void endMessage() {
        System.out.println(PREFIX + "Bye! Have a nice day!");
    }

    public static void showTaskAdded(TaskList taskList) {
        System.out.println("Laika: Gotcha! I've added the task for you\n  "
                + taskList.getTasks(taskList.getSize() - 1) + System.lineSeparator()
                + "You have " + (taskList.getSize()) + " tasks in your list. :P");
    }
}
