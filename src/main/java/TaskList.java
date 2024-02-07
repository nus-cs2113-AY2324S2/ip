import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public int size() {
        return tasks.size();
    }

    public void displayAll() {
        IntStream.rangeClosed(1, tasks.size())
                .mapToObj(index -> index + "." + getTask(index))
                .forEach(System.out::println);
    }
}
