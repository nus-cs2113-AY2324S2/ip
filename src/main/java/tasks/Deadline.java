package tasks;

/**
 * Represents a deadline task in KikuBot.
 * A deadline task has a due date/time in addition to a description.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline instance with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The due date/time of the deadline task.
     */
    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task formatted for printing,
     * including the type of the task, its status, description, and its due date/time.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline task formatted for file storage,
     * including the type of the task, its done status, description, and due date/time,
     * separated by a pipe character.
     *
     * @return A string formatted for saving the deadline task to a file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
