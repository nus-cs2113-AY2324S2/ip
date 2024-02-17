package jeff;
import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void add(Task task) {
        tasks.add(task);
    }

    public static int size() {
        return tasks.size();
    }

    public static boolean isEmpty() {
        return tasks.isEmpty();
    }

    public static Task get(int index) {
        return tasks.get(index);
    }

    public static Task back() {
        return tasks.get(tasks.size() - 1);
    }

    public static Task remove(int index) {
        return tasks.remove(index);
    }
}
