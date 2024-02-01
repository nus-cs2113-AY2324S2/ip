public class List {
    Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String description) {
        tasks[tasksCount] = new Task(description);
        tasksCount++;
    }
}
