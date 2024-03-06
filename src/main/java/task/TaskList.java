package task;

import java.util.ArrayList;

public class TaskList {

    private int taskCount = 0;
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int taskCount() {
        return this.taskCount;
    }
}
