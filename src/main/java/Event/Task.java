package Event;

/**
 * The <code>Task</code> class represents a generic task within the application.
 * This class serves as a base class for more specific types of tasks and
 * includes fundamental attributes and methods that are common across all task types.
 */
public class Task {
    public String description;
    public boolean isDone;
    protected String eventType;

    /**
     * Constructs a new Task with the given description.
     * By default, the task is not completed.
     *
     * @param description The textual description of the task.
     */
    public Task(String description) {

        this.description = description;
        this.isDone = false;
        this.eventType = null;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the event type icon. Which is intended to be overridden
     * by subclasses to return specific icons representing the task type.
     *
     * @return The event type icon as a String.
     */
    public String getTasksIcon() {
        return eventType;
    }

    /**
     * Returns a string indicating the task's completion status.
     *
     * @return A string representation of the task's completion status.
     * "[X]" if the task is done, and "[ ]" if it's not.
     */
    public String getTasksStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of the task, including its type,
     * status, and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return this.getTasksIcon() + this.getTasksStatus() + " " + this.description;
    }

    /**
     * Provides a string formatted for storage purposes. This method is intended
     * to be overridden by subclasses to return a string that represents the task's
     * complete state in a format suitable for storage.
     *
     * @return A string representation of the task for storage purposes.
     */
    public String toStorageString() {
        return "";
    }

    /**
     * Retrieves the task's description.
     *
     * @return The task's description as a String.
     */
    public String getDescription() {
        return this.description;
    }
}

