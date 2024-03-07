package Bobble.task;

/**
 * The ToDo class represents a task without a deadline or duration.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of a Todo task.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
