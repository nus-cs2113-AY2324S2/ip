public class List {
    protected static Task[] tasks;

    public static int totalTasks = 0;

    public List() {
        this.tasks = new Task[100];
    }

    public static Task[] getTasksArray() {
        return tasks;
    }
}
