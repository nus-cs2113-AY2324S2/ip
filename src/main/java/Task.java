public class Task {
    public static final String OUTPUT_INDENTATION = "    ";
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, boolean b) {
        this.description = description;
        this.isDone = b;
        this.taskType = "";
    }

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
