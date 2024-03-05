package cody.tasks;

/**
 * The Deadline class represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the description of the task along with its deadline.
     *
     * @return The description of the task with its deadline.
     */
    @Override
    public String getDescription() {
        return description + " (by: " + by + ")";
    }

    /**
     * Returns the type of the task, which is "D" for Deadline.
     *
     * @return The type of the task.
     */
    @Override
    public String getTaskType() {
        return "D";
    }
}
