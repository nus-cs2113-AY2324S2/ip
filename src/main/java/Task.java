public class Task {
    protected String description;
    protected boolean isDone;
    private static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
    public static int getTotalTasks() {
        return totalTasks;
    }
}
