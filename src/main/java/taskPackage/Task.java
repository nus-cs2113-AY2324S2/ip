package taskPackage;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon ("[X]" if completed, "[ ]" if not completed).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts the task to a string for writing to a file.
     *
     * @return The string representation of the task for file storage.
     */
    public String toFileString() {
        if (this instanceof ToDos) {
            return "T | " + (isDone ? "1" : "0") + " | " + getDescription();
        } else if (this instanceof Deadlines) {
            Deadlines deadline = (Deadlines) this;
            String description = getDescription();
            String byDate = deadline.getByDate();

            if (description.contains(" (by: ")) {
                description = description.substring(0, description.indexOf(" (by: "));
            }
            return "D | " + (isDone ? "1" : "0") + " | " + description
                    + (byDate.isEmpty() ? "" : " | " + byDate);
        } else if (this instanceof Events) {
            Events event = (Events) this;
            String description = getDescription();
            String fromDate = event.getFromDate();
            String toDate = event.getToDate();

            if (description.contains(" (from: ")) {
                description = description.substring(0, description.indexOf(" (from: "));
            }
            return "E | " + (isDone ? "1" : "0") + " | " + description + (fromDate.isEmpty() ? "" : " " +
                    "| from " + fromDate + (toDate.isEmpty() ? "" : " to " + toDate));
        } else {
            return "Unknown task";
        }
    }
}
