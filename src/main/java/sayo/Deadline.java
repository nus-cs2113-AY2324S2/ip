package sayo;

/**
 * Represents a deadline task.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs an Event task with the specified description, and deadline time.
     *
     * @param description The task's description.
     * @param by The deadline time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline, including its deadline time.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation of the Deadline task formatted for file storage.
     * Prefixes the base task file format with "D |" to denote a deadline task.
     *
     * @return A string formatted for saving to a file.
     */
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }

    /**
     * Creates a Deadline object from a string format.
     * Parses the string based on the predefined format and creates an Event object accordingly.
     *
     * @param fileFormat A string representing the task in file format.
     * @return A new Deadline object populated with the data from the fileFormat string.
     */
    public static Task fromFileFormat(String fileFormat) {
        // Assuming fileFormat is in the correct format for Deadline
        String[] parts = fileFormat.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String by = parts[3].trim();

        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }
}
