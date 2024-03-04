package sayo;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as not done by setting its status to false.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns the status icon representing the completion of the task.
     *
     * @return A string representation of the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); 
    }

    /**
     * Returns the string representation of the task formatted for file storage.
     *
     * @return A string formatted for saving to a file.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description; // This basic format will be extended by subclasses
    }

    /**
     * Creates a Task object from a string format, intended to be overridden in subclasses.
     *
     * @param fileFormat A string representing the task in file format.
     * @return A new Task object.
     * @throws SayoException If the method is called on Task directly instead of a subclass.
     */
    public static Task fromFileFormat(String fileFormat) throws SayoException {
        throw new SayoException("This method should be called on a subclass, not on the Task class directly.");
    }
    
    /**
     * Returns the string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
