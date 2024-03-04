package gab.task;

import java.util.ArrayList;

/**
 * Dynamic arraylist that stores all the tasks
 */
public class TaskList {
    public ArrayList<Task> taskList;

    /**
     * Constructs a new taskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Add task into taskList
     *
     * @param task task to be added into taskList
     */
    public void addToList (Task task) {
        taskList.add(task);
    }

    /**
     * Retrieve task count in taskList
     *
     * @return exisiting task count
     */
    public int getTaskCount () {
        return taskList.size();
    }
}
