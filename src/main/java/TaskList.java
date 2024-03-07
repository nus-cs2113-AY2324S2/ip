import java.util.ArrayList;

/**
 * TaskList class contains operation methods to manipulate list.
 */
public class TaskList {
    /**
     * Prints out every task in the list.
     *
     * @param tasks The array list containing tasks inputted.
     */
    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(Messages.PRINT_TASK_LIST);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            tasks.get(i).printTask();
        }
    }

    /**
     * Checks if user's command is a "mark" or "unmark" command.
     *
     * @param tasks The array list containing tasks inputted.
     * @param taskNumber An integer representing task number of the task to be marked or unmarked.
     * @param arrayOfCommand An array storing the command type and task number.
     */
    public static void markAndUnmarkTask(ArrayList<Task> tasks, int taskNumber, String[] arrayOfCommand) {
        if (arrayOfCommand[0].equals("mark")) {
            System.out.println(Messages.MARK_DONE_MESSAGE);
            tasks.get(taskNumber).markAsDoneOrNotDone(arrayOfCommand);
        } else {
            System.out.println(Messages.UNMARK_DONE_MESSAGE);
            tasks.get(taskNumber).markAsDoneOrNotDone(arrayOfCommand);
        }
        System.out.print(" ");
        tasks.get(taskNumber).printTask();
    }

    /**
     * Handles "mark" and "unmark" command by marking task as done or unmarking task as undone.
     *
     * @param userCommand A string presenting user's command.
     * @param arrayOfCommand An array to store the command type and task number.
     * @param tasks The array list containing tasks inputted.
     * @param taskCount An integer to keep track of the number of tasks in the list.
     */
    public static void handleMarkAndUnmarkRequest(String userCommand, String[] arrayOfCommand, ArrayList<Task> tasks,
                                                  int taskCount) {
        arrayOfCommand = userCommand.split(" ", 2);
        try {
            if (arrayOfCommand.length < 2 || arrayOfCommand[1].isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            int taskNumber = Integer.parseInt(arrayOfCommand[1]);
            if (taskNumber > taskCount) {
                throw new NullPointerException();
            }
            markAndUnmarkTask(tasks, taskNumber - 1, arrayOfCommand);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(Messages.MARK_AND_UNMARK_REQUEST_FORMAT);
        } catch (NullPointerException e) {
            System.out.print(Messages.OUT_OF_BOUND);
        }
    }

    /**
     * Handles "todo", "deadline" and "event" command.
     *
     * @param userCommand A string presenting user's command.
     * @param arrayOfCommand An array to store the command type and timings.
     * @param tasks The array list containing tasks inputted.
     */
    public static void handleTodoDeadlineAndEvent(String userCommand, String[] arrayOfCommand, ArrayList<Task> tasks) {
        if (userCommand.startsWith("todo")) {
            arrayOfCommand = userCommand.split(" ", 2);
            Todo todo = new Todo(arrayOfCommand[1]);
            tasks.add(todo);
        }

        if (userCommand.startsWith("deadline")) {
            arrayOfCommand = userCommand.split("deadline | /by");
            Deadline deadline = new Deadline(arrayOfCommand[1], arrayOfCommand[2]);
            tasks.add(deadline);
        }

        if (userCommand.startsWith("event")) {
            arrayOfCommand = userCommand.split("event | /from | /to ");
            Event event = new Event(arrayOfCommand[1], arrayOfCommand[2], arrayOfCommand[3]);
            tasks.add(event);
        }

        System.out.println(Messages.TASK_ADDED_MESSAGE);
        tasks.get(tasks.size() - 1).printTask();
    }

    /**
     * Handles "remove" command.
     *
     * @param tasks The array list containing tasks inputted.
     * @param taskNumber An integer representing task number of the task to be removed.
     */
    public static void removeTask(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).printTask();
        tasks.remove(taskNumber);
    }

    /**
     * Handles "find" command.
     *
     * @param tasks The array list containing tasks inputted.
     * @param keyword A string representing the keyword inputted by user.
     */
    public static void findKeyword(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        printTasks(tasksWithKeyword);
    }
}
