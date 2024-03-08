/**
 * The Todo class represents a simple todo task.
 * It extends the Task class and does not add any additional properties.
 */

public class Todo extends Task {
    /**
     * Constructs a new Todo object with the given description.
     * @param description The task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * @return The string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + description;
    }

    /**
     * Returns a string representation of the Todo task suitable for writing to a file.
     * @return The string representation
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}