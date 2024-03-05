import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    //Constructor for if there already exists tasks to be loaded in
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    //Constructor for if there are no tasks to load - create a new ArrayList
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public Task getTaskAt(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
