
public class Task {
    private final String DESSCRIPTION;
    private boolean isDone;
    protected static int taskCount = 0;

    public Task (String description) {
        this.DESSCRIPTION = description;
        this.isDone = false;
        taskCount++;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() { isDone = false; }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public static int getTaskCount() { return taskCount; }

    @Override
    public String toString() {
        return getStatusIcon() + " " + DESSCRIPTION; //will be over ride by subclass
    }
}

