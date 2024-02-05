public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String printTask() {
        return "[" + getStatusIcon() + "]" + getDescription();
    }

}
