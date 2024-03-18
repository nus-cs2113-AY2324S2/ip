package duke;

import java.util.ArrayList;

/**
 * Class that holds all the parsed tasks
 */
public class TaskList {
    private static ArrayList<Task> parsedTaskList = new ArrayList<>(2);

    /**
     * Returns the Task at the chosen index
     *
     * @param i The chosen index
     * @return The chosen Task
     */
    public Task get(int i) {
        return parsedTaskList.get(i);
    }

    /**
     * Adds the Task into the list
     *
     * @param input Task that is to be added to the list
     */
    public void add(Task input) {
        parsedTaskList.add(input);
    }

    /**
     * Removes chosen Task from list
     *
     * @param input Task to be removed
     */
    public void remove(Task input) {
        parsedTaskList.remove(input);
    }

    /**
     * Returns the current size of the task list
     *
     * @return Length of list
     */
    public int size() {
        return parsedTaskList.size();
    }
}
