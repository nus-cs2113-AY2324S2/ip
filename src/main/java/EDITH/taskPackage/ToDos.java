package EDITH.taskPackage;

/**
 * Represents a to-do task, which is a type of task with no specific deadline or timeframe.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDos object with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the status icon of the to-do task, which is "[T]".
     *
     * @return The status icon of the to-do task.
     */
    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
