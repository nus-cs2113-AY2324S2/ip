package Event;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    public List<Task> findTasks(String word) {
        List<Task> matchingTask = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(word)) {
                matchingTask.add(task);
            }
        }
        return matchingTask;
    }
}
