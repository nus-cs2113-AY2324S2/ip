package EDITH.taskPackage;

/**
 * Represents a deadline task, which is a type of task with a specific deadline.
 */
public class Deadlines extends Task {
    protected String by;

    /**
     * Constructs a Deadlines object with the specified
     * description, deadline, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the task.
     * @param isDone      The completion status of the deadline task.
     */
    public Deadlines(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getByDate() {

        return by;
    }

    /**
     * Returns the status icon of the deadline task, which is "[D]".
     *
     * @return The status icon of the deadline task.
     */
    @Override
    public String getStatusIcon() {

        return "[D]" + super.getStatusIcon();
    }

    /**
     * Returns the description of the deadline task along with its deadline.
     *
     * @return The description of the deadline task along with its deadline.
     */
    @Override
    public String getDescription() {

        return super.getDescription() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {

        return getStatusIcon() + " " +getDescription();
    }
}
