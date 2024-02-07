public class TaskList {
    protected Task[] list;
    protected int count;

    public TaskList() {
        this(new Task[100], 0);
    }

    public TaskList(Task[] list, int count) {
        this.list = list;
        this.count = count;
    }

    public void addTask(Task task) {
        this.list[count] = task;
        this.count = count + 1;
        task.sequence = count;
    }

    public int getCount() {
        return count;
    }
}
