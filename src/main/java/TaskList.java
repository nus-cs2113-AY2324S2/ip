import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks != null ? loadedTasks : new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int index, boolean isDone) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number is out of bounds.");
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number is out of bounds.");
        }
        return tasks.remove(index);
    }
}
