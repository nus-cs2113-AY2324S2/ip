package task;

/**
 * The ToDo class represents a task without any date and time attached to it.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo object.
     *
     * @return The string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
