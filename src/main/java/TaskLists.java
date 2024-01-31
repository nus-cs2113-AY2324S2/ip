import java.util.List;
import java.util.ArrayList;

public class TaskLists {
    private final List<Tasks> tasksList;

    public TaskLists() {
        this.tasksList = new ArrayList<>();
    }

    public void addTask(Tasks task) {
        this.tasksList.add(task);
    }

    public void box(int index) {
        Tasks fetch = this.tasksList.get(index - 1);
        fetch.mark();
        this.tasksList.set(index - 1, fetch);
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + fetch.toString());
        System.out.println("____________________________________________________________");
    }

    public void unbox(int index) {
        Tasks fetch = this.tasksList.get(index - 1);
        fetch.unMark();
        this.tasksList.set(index - 1, fetch);
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + fetch.toString());
        System.out.println("____________________________________________________________");
    }

    public void printTasks() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasksList.get(i).toString());
        }
    }
}
