
public class Task { //each task represent one instance of this class
    private String description;
    private boolean isDone;
    protected static int taskCount = 0;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
        taskCount++; //increment everytime constructor is called
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() { //need to choose which task to mark as done
        isDone = true;
    }

    public void markAsNotDone() { isDone = false; }

    public String getStatusIcon() { //if it is done mark as [X]
        return (isDone ? "[X]" : "[ ]");
    }

    public static int getTaskCount() { return taskCount; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description; //will be over ride by subclass
    }
}

