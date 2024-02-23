package gab.task;

/**
 * Deadline class which creates deadline task with deadline name and deadline
 */
public class Deadline extends Task {
    public static final String DEADLINE_STATUS = "[D]";
    private final String BY;

    /**
     * Initialise deadline task with the name and due date
     *
     * @param description deadline name
     * @param by due date of task
     */
    public Deadline (String description, String by) {
        super(description);
        this.BY = by;
    }

    /**
     * Returns format of deadline tasks to be printed
     *
     * @return string format of deadline task to be printed
     */

    @Override
    public String toString() {
        return DEADLINE_STATUS + super.toString() + " " + "(by: " + BY + ")"; //superclass is task
    }

    /**
     * Convert deadline task to a file format to be displayed in external data file
     *
     * @return format of deadline task to be display
     */

    @Override
    public String toFileFormat() {
        return DEADLINE_STATUS + super.toFileFormat() + " | " + "by: " + BY;
    }
}
