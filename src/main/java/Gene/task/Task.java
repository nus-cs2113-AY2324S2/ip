package Gene.task;

/**
 * Represents the general task of the Gene chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }


    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toFileString() {
        return description;
    }
}