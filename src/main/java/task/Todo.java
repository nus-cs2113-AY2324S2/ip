package carrot.task;

/**
 * Represents a todo task, a type of task that requires only a description
 * <p>
 * A todo task inherits properties and behaviors from the {@link Task} class.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the specified description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task with the specified description and completion status.
     *
     * @param description the description of the todo task
     * @param isDone      the completion status of the todo task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo task.
     * <p>
     * The string includes the task type indicator "[T]" followed by the status icon and description of the task.
     *
     * @return a string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the todo task in a format suitable for storing in a file.
     * <p>
     * The todo task is formatted with the task type indicator "T" and then follows the format of the parent task.
     *
     * @return the todo task in file format
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat();
    }
}
