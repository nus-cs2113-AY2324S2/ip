package allez.task;

/**
 * Deadline are tasks that have a specific end timing, noted as /by.
 * Inherits Task Class.
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Obtain the end time of the task.
     *
     * @return /by of the task
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the /by of the task
     * @param by end time of the task to be specified
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns all the details of the task as a string.
     *
     * @return task type, task status, task description, /by
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
