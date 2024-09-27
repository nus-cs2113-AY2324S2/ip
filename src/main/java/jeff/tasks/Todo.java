package jeff.tasks;

import jeff.Task;

/**
 * Represents a todo task.
 * Extends the Task class and provides functionality specific to todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return String representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task suitable for storing in a file.
     *
     * @return String representing the todo task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
