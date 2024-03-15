package Byte.task;

/**
 * Represents a todo task in the task list.
 */
public class ToDo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task with the given description and status.
     *
     * @param description The description of the todo task.
     * @param isDone      The status of the todo task.
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task for writing to a file.
     *
     * @return A string representation of the todo task for writing to a file.
     */
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
