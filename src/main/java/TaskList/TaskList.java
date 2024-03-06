package TaskList;

import java.util.ArrayList;

/**
 * A class that contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    public static ArrayList<Task> tasks;

    public void newTasks() {
        tasks = new ArrayList<>();
    }
    public static Task newSpecialTask(String description) {
        return AddTask.addSpecialTask(description);
    }
}
