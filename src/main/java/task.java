public class task {
    protected String description;
    protected boolean isDone;

    public task(String description, boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void markAsDone() {
        this.isDone = true;
    }
}
