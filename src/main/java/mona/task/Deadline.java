package mona.task;

/**
 * Represents a deadline task with a description and a due date. The Deadline class
 * is a subclass of the Task class.
 */
public class Deadline extends Task {
    protected String by;
    /**
     * Constructor for Deadline. Initializes the task with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Returns a string representation of the deadline task, including its type, status, description, and due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " +  "(" + "by: " + this.by + ")";
    }
    /**
     * Returns the full description of the deadline task, including its description and due date.
     *
     * @return The full description of the deadline task.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " "
                + "/by " + this.by;
    }
}
