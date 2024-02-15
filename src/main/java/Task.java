public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() throws LovieException {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    public void markAsDone() {

        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getTaskIcon() {
        return "";
    }

    public String getTimespan() {
        return "";
    }
}
