import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> list;
    protected int count;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int getCount() {
        return list.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < getCount(); i++) {
            System.out.println(i+1 + "." + this.list.get(i));
        }
    }

    public ArrayList<Task> getTasks() {
        return list;
    }

    public void removeTask(int sequence) {
        Task task = this.list.get(sequence);
        this.list.remove(sequence);
        System.out.println("Noted. I've removed this task:\n"
                + task
                + "\n"
                + "Now you have "
                + getCount()
                + " tasks in the list.");
    }
}