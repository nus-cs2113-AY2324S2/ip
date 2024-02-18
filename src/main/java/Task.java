public class Task {
    public static final String OUTPUT_INDENTATION = "    ";
    protected String description;
    protected boolean isDone;
    protected Integer index;

    public Task(String description, boolean b) {
        this.description = description;
        this.isDone = b;
    }

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
