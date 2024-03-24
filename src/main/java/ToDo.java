/**
 * Represents a to-do task in the Phoebe application.
 * A to-do task is a basic task type that consists of a description and completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     * The task is initialized as not completed by default.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task, including its type, status,
     * and description.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the to-do task into a format suitable for file storage.
     * This implementation calls the superclass's toFileFormat method directly, as to-do tasks
     * do not have additional fields beyond those handled by the Task class.
     *
     * @return A string representation of the to-do task in a file storage format.
     */
    @Override
    public String toFileFormat() {
        // Directly use Task's implementation as ToDo doesn't add additional information.
        return super.toFileFormat();
    }
}
