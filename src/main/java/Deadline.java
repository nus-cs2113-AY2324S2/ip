import java.util.NoSuchElementException;

/**
 * The Deadline class represents a deadline task, a type of Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     * @param description Description of the deadline task.
     * @throws IllegalArgumentException if the description is empty or contains only "deadline."
     * @throws NoSuchElementException if the description does not contain "/by" keyword.
     */
    public Deadline(String description) {
        super(description);
        String[] parts = description.split(" /by ", 2);
        if (description.trim().equalsIgnoreCase("deadline")) {
            throw new IllegalArgumentException("The description of a deadline cannot be empty.");
        }
        if (parts.length != 2) {
            throw new NoSuchElementException("Invalid deadline format: missing '/by' keyword.");
        }
        this.description = parts[0].substring(9).trim(); // Remove "deadline" and trim
        this.by = parts[1];
        this.taskType = "[D]";
    }

    /**
     * Constructs a Deadline object with the specified mark status, description, and deadline.
     * @param isDone Mark status of the deadline task.
     * @param description Description of the deadline task.
     * @param by Deadline of the deadline task.
     */
    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
        this.taskType = "[D]";
    }

    /**
     * Retrieves the deadline of the deadline task.
     * @return Deadline of the deadline task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Converts the deadline task to a string representation.
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the deadline task to a string representation for file storage.
     * @return String representation for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString();
    }
}
