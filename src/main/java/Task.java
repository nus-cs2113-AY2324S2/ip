public class Task {
    private String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
    // Returns the status icon and description of the task
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String toFileString() {
        String status = isDone ? "1" : "0";
        return status + " | " + description;
    }
}
