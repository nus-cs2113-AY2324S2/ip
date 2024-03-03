package ava.task;

/**
 * Represents a normal task.
 */
public class ToDo extends Task {

    public ToDo (String description) {
        super(description);
    }

    public ToDo (String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Converts a Todo object to a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
