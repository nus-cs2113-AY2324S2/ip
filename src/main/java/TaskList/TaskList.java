package TaskList;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks;

    public void newTasks() {
        tasks = new ArrayList<>();
    }
    public static Task newSpecialTask(String description) {
        return AddTask.addSpecialTask(description);
    }
}
