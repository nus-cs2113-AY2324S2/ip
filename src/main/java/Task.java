class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getType() {
        return "";
    }

    public String toString() {
        return getType() + " " + getStatusIcon() + " " + description;
    }
}