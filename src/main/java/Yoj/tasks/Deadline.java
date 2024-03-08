package Yoj.tasks;
public class Deadline extends Task {
    protected String by;
    /**
     * Returns the type identifier for the task.
     *
     * @return A string representing the type of the task, which is "[D]" for Deadline.
     */
    public String taskType() {
        return "[D]";
    }
    /**
     * Constructor for a Deadline task.
     *
     * @param description The description of the task.
     * @param by The time by which the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Provides a string representation of the Deadline task including its type, status, description, and deadline.
     *
     * @return A string representing the Deadline task, formatted with its deadline time.
     */
    @Override
    public String toString() {
        return taskType() + super.toString() + " (by: " + by + ")";
    }
}