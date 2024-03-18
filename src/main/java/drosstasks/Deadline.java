package drosstasks;

import drosstasks.Task;

/**
 * Represents a deadline task with a specific due date.
 * Inherits from the Task class, adding a due date attribute to the task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline instance with a description and a due date.
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its status,
     * description, and due date.
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        String statusMark = this.isCompleted() ? "x" : " "; // Mark with 'x' if completed
        return "[D]" + "[" + statusMark + "] " + super.toString() + " (by: " + by + ")";
    }
}
