import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public List<Task> searchTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            // Implement search logic to check for keyword in task description and other relevant fields
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
