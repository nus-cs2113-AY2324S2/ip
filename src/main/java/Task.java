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
        return description + "|" + (isDone ? "1" : "0");
    }

    public static Task fromString(String fileString) {
        String[] parts = fileString.split("\\|");
        String taskType = parts[0];
        String description = parts[1];
        boolean isDone = parts[2].equals("1");

        Task task;
        switch (taskType) {
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        case "T":
            task = new Todo(description);
            break;
        default:
            task = null;
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }


}
