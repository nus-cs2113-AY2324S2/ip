/**
 * Represents a to-do task.
 * Inherits from the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructor for creating a new ToDo object.
     *
     * @param description The description of the to-do task.
     * @param isDone      Boolean value indicating whether the task is done or not.
     */
    public ToDo(String description, Boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return A string containing the status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[T] " + super.getStatusIcon() + " " + super.toString();
    }
}
