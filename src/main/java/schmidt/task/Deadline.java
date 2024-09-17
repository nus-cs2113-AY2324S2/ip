package schmidt.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    public static final String LETTER = "D";
    public static final String REGEX_BY_DELIMITER = "(?<!\\w)/by(?=\\s|$)";
    public static final int DESCRIPTION_INDEX = 0;
    public static final int BY_INDEX = 1;
    public static final String INCORRECT_FORMAT_MESSAGE = "Please follow the deadline command format\n" +
            "\tdeadline <description> /by <date>";
    protected String by;

    /**
     * Constructs a deadline task with the specified description and deadline.
     *
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return the deadline of the task.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return a string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + LETTER + "]" + super.toString() + " (by: " + by + ")";
    }
}
