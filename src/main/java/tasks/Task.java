package tasks;

public class Task {
    protected String description;
    protected boolean isDone;



    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " "; // mark done task with X
    }

    public void markAsDone() {
        this.setDone(true);
    }

    public void markAsUndone() {
        this.setDone(false);
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toFileString() {
        return String.format("task|%s|%s", isDone ? "1" : "0", getDescription());
    }

    public String taskString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
