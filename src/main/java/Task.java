/**
 * This abstract class represents a generic task in the task list.
 * It provides basic functionalities such as setting the description,
 * marking/unmarking the task as completed, and retrieving the status icon.
 *
 * Subclasses like `ToDo`, `Deadline`, and `Event` implement specific
 * functionalities for different task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isEmpty;

    /**
     * Constructor for the Task class.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isEmpty = description.trim().isEmpty();
    }

    /**
     * Returns the status icon of the task, "[X]" for completed and "[ ]" for not completed.
     *
     * @return the status icon string
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as completed by setting the `isDone` flag to true.
     */
    public void markList() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as completed by setting the `isDone` flag to false.
     */
    public void unmarkList() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Abstract method to convert the task to a string representation suitable for storing in a file.
     * Subclasses should implement this method to provide their specific format.
     *
     * @return the string representation of the task for file storage
     */
    public abstract String toFileString();
}
