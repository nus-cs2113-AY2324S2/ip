package Tasks;

/**
 * The Deadline class represents a deadline task.
 * It extends the Task class and provides specific behavior for deadline tasks.
 */
public class Deadline extends Task {
    /** The due date of the deadline task. */
    protected String dueDate;

    /**
     * Constructs a new Deadline object with the given content, completion status, and due date.
     *
     * @param content   The content of the deadline task.
     * @param isDone    The completion status of the deadline task (true if completed, false otherwise).
     * @param by        The due date of the deadline task.
     */
    public Deadline(String content, boolean isDone, String by) {
        super(content, isDone);
        this.dueDate = by;
    }

    /**
     * Returns a string representation of the deadline task.
     * The string representation includes the task type identifier "[D]", its content, and due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
