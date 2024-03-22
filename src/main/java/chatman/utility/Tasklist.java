package chatman.utility;

import chatman.tasks.Task;

import java.util.ArrayList;

/**
 * Contains the task list itself. Also, implements functionality to return, add & remove task objects from list of
 * stored tasks and retrieve list size.
 *
 * @author LWachtel1
 * */
public class Tasklist {
    public static final int MAX_NUM_TASKS = 100; //maximum number of tasks to be stored at once
    private static ArrayList<Task> storedTasks = new ArrayList<>();  //provides storage for task objects


    /**
     * Provides access to current list of stored tasks by returning arraylist reference.
     *
     * @return Reference to arraylist containing currently stored tasks.
     **/
    public static boolean isTasklistEmpty() {
        return storedTasks.isEmpty();
    }

    /**
     * Returns reference to task object at given index in task list.
     *
     * @return Reference to task object at given index in task list.
     **/
    public static Task getTask(int index) {
        return storedTasks.get(index);
    }

    /**
     * Allows Task-type object to be added to current list of stored tasks.
     **/
    public static void addTask (Task taskToAdd) {
        storedTasks.add(taskToAdd);
    }

    /**
     * Allows Task-type object at given index to be removed from current list of stored tasks.
     */
    public static void removeTask (int index) {
        storedTasks.remove(index);
    }

    /**
     * Returns current number of stored tasks in task list.
     *
     * @return Current size of task list (current number of elements).
     **/
    public static int getSize () {
        return storedTasks.size();
    }
}
