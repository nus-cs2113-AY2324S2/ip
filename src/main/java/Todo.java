/**
 * Represents a to-do task without a specific deadline.
 * A {@code Todo} object encapsulates the details of a task that needs to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} with the specified task description.
     * Initializes a new to-do task with the provided description.
     *
     * @param description The text description of the to-do task.
     */
    public Todo(String description) {
        super(description); // Calls the superclass Task constructor with the description.
    }

    /**
     * Returns the string representation of the to-do task in a format suitable for file storage.
     * This method leverages the {@code toFileFormat} method from the superclass {@code Task}.
     *
     * @return A string representation of the to-do task for file storage, maintaining the format defined in {@code Task}.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat(); // Uses the Task class's method for file formatting.
    }

    /**
     * Returns the string representation of the to-do task, including its type, status (done or not done),
     * and description. Overrides the {@code toString} method from the superclass {@code Task}.
     *
     * @return A string representation of the to-do task, formatted with its type and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString(); // Adds the to-do type identifier "[T]" before the standard task representation.
    }
}
