import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    TaskList add(Task task) {
        TaskList newList = new TaskList(this.tasks);
        newList.tasks.add(task);
        return newList;
    }

    Task get(int index) {
        return this.tasks.get(index);
    }

    TaskList set(int index, Task task) {
        TaskList newList = new TaskList(this.tasks);
        newList.tasks.set(index, task);
        return newList;
    }

    TaskList remove(int index) {
        TaskList newList = new TaskList(this.tasks);
        newList.tasks.remove(index);
        return newList;
    }

    int size() {
        return this.tasks.size();
    }

    List<Task> getTasks() {
        return this.tasks;
    }

    void printTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.size(); ++i) {
            result.append(String.format("%s. %s%n", (i + 1), this.get(i)));
        }
        System.out.println(result);
    }

    void findTasks(String keyword) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < this.size(); ++i) {
            Task task = this.get(i);
            if (task.toString().contains(keyword)) {
                results.append(String.format("%s. %s%n", (i + 1), this.get(i)));
            }
        }
        System.out.println(results);
    }

}
