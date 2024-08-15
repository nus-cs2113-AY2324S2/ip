package bobby;

/**
 * Represents a todo task, which is a type of task.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo instance with the given description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone Indicates whether the todo task is done or not.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task, including its status icon and description,
     * prefixed with "[T]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
