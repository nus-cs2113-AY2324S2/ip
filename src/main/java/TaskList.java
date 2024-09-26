import Tasks.Task;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks.
 * It provides methods to add, retrieve, update, and delete tasks from the list.
 */
public class TaskList {
    /**
     * The ArrayList to store tasks.
     */
    private static ArrayList<Task> taskArrayList;

    /**
     * Constructs a new TaskList object with the given ArrayList of tasks.
     *
     * @param taskList The ArrayList containing tasks to be managed by the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        TaskList.taskArrayList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public static void add(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param itemIndex The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public static Task get(int itemIndex) {
        return taskArrayList.get(itemIndex);
    }

    /**
     * Sets the status of a task (done or not done) based on its index.
     *
     * @param itemIndex The index of the task to update.
     * @param isDone    The status to set for the task (true for done, false for not done).
     */
    public static void setDone(int itemIndex, boolean isDone) {
        taskArrayList.get(itemIndex).setDone(isDone);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param itemIndex The index of the task to delete.
     */
    public static void delete(int itemIndex) {
        taskArrayList.remove(itemIndex);
    }

    /**
     * Clears the task list, removing all tasks.
     */
    public static void clearList() {
        taskArrayList.clear();
    }

    public static void findAndPrint(String keyword) {
        boolean hasMatch = false;
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.getContent().contains(keyword)) {
                hasMatch = true;
                tempTaskList.add(task);
            }
        }
        if (hasMatch) {
            String taskPlural = tempTaskList.size() == 1 ? "task" : "tasks";
            System.out.println("Here are the matching " + taskPlural + " in your list:");
            int iterator = 0;
            for (Task task : tempTaskList) {
                iterator += 1;
                System.out.println(iterator + ". " + task);
            }
        }
        else {
            System.out.println("No matches found");
        }
    }
}
