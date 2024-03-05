/**
 * Represents a todo task, which is a type of task without a deadline or specific timing.
 */
public class Todo extends Task {
    /**
     * Creates a new todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new todo task with the given description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      The completion status of the todo task.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the todo task, including its completion status and description.
     *
     * @return A string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task for saving to a file, including its completion status and description.
     *
     * @return A string representing the todo task for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
