public abstract class Task {
    private final String description;
    private boolean isDone;
    public abstract String getType();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
//            return status + " " + description;
        return "[" + getType() + "]" + status + " " + description;
    }

}
