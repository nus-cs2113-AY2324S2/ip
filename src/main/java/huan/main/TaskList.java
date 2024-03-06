package huan.main;

import huan.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing the list of tasks, include methods for checking index validity, adding task and clear entire list
 */
public class TaskList {
    public static List<Task> tasks = new ArrayList<>();

    /**
     * Method for checking whether the given index is valid
     * @param index the given index, can used for either marking, unmarking, and deleting the indexed tasks
     * @return whether the index in within range of the list
     */
    public static Boolean isIndexValid(int index) {
        return index >= 1 && index <= tasks.size();
    }

    /**
     * Method for adding a task to the List
     * @param newTask the task to be added
     */
    public static void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Method for clearing the list, in case of corrupt or empty input
     */
    public static void clearTasks() {
        tasks.clear();
    }

}
