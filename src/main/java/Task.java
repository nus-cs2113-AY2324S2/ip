/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isCompleted;
    protected String taskType;

    /**
     * Constructor for the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
        taskType = "";
    }

    /**
     * Generates a string representation of the task for saving.
     *
     * @return A string representation of the task.
     */
    public String saveTaskRepresentation() {
        // An example of the format: "task|0"
        return description + "|" + (isCompleted ? "1" : "0");
    }

    /**
     * Loads a task from its string representation.
     *
     * @param fileString The string representation of the task.
     * @return The loaded Task object.
     */
    public static Task loadTaskRepresentation(String fileString) {
        String[] fileEntryIntoParts = fileString.split("\\|");
        String taskType = fileEntryIntoParts[0];
        String description = fileEntryIntoParts[1];
        boolean isCompleted = fileEntryIntoParts[2].equals("1");

        Task task;
        switch (taskType) {
        case "D":
            task = new Deadline(description);
            break;
        case "E":
            task = new Event(description);
            break;
        case "T":
            task = new ToDo(description);
            break;
        default:
            // Handle unknown task type
            task = null;
            break;
        }

        if (task != null && isCompleted) {
            task.markAsCompleted(); // Mark task as done if status is '1'
        }

        return task;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon ('✔' for completed, ' ' for incomplete).
     */
    public String getStatusIcon() {
        return (isCompleted ? "✔" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotCompleted() {
        isCompleted = false;
    }
}
