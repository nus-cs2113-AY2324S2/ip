package chris.tasktypes;

import java.util.ArrayList;
public class taskList {
    protected static ArrayList<Task> tasks;
    protected static int taskCount = 0;
    public taskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTask(String taskNumber){
        int index = Integer.parseInt(taskNumber);
        if (tasks.get(index - 1).markTask()) {
            System.out.println("Task marked!");
        } else {
            System.out.println("Task unmarked!");
        }
    }

    public Task deleteTask(String taskNumber){
        int index = Integer.parseInt(taskNumber);
        Task deletedTask = tasks.remove(index - 1);
        taskCount--;
        return deletedTask;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void printTaskList() {
        for (Task i : tasks) {
            System.out.println(i);
        }
    }
}
