public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String toFileString() {
        // Implement how you want to represent the task as a string for file storage
        // For example, you can concatenate the description and status with a delimiter
        return description + "|" + (isDone ? "1" : "0"); // Example format: "description|status"
    }

    public static Task fromString(String fileString) {

        String[] parts = fileString.split("\\|");
        String description = parts[0];
        boolean isDone = parts[1].equals("1");
        Task task = new Task(description);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

}
