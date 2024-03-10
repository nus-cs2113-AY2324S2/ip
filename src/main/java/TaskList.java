import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void markTaskDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markDone();
        }
    }

    public void markTaskNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markNotDone();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
