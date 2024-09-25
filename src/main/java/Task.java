/**
 * Task class represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return Status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a string representation for display.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to a string representation for file storage.
     *
     * @return String representation of the task for file storage.
     */
    public String toFileString() {
        return description + "|" + (isDone ? "1" : "0");
    }

    // Author: PradeepM
    /**
     * Creates a Task object from the string representation read from a file.
     *
     * @param fileString String representation of the task from a file.
     * @return Task object created from the fileString.
     */
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
    // Author: PradeepM
}
