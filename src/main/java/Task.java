public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
        if (parts.length < 3) { // Ensure there are at least 3 parts
            return null; // Or handle this case appropriately
        }
        String taskType = parts[0];
        String description = parts[1];
        boolean isDone = parts[2].equals("1");
        Task task;
        switch (taskType) {
        case "D":
            if (parts.length < 4) { // Ensure there are enough parts for a Deadline
                return null; // Or handle this case appropriately
            }
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 5) { // Ensure there are enough parts for an Event
                return null; // Or handle this case appropriately
            }
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
