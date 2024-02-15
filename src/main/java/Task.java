public abstract class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String getTypeDisplay();

    public String getMarkDisplay() {
        if (this.isDone()) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public abstract String getData();
}
