/**
 * Represents a task with a deadline.
 *
 * Deadline object contains information about a task that has a deadline,
 * including its description and the date by which it should be completed.
 *
 * @param description the description of the task
 * @param by the deadline of the task
 * @return a Deadline object representing the task with the specified description and deadline
 * @throws DukeException if there is an error in processing the task
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + description + " (by: " + by + ")";
    }
}
