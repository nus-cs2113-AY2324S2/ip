package lovie.task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Returns the description of the deadline.
     *
     * @return The description of the deadline.
     */
    @Override
    public String getDescription() {
        return description.split("(?i)/by ")[0].split("(?i)deadline")[1].trim();
    }

    /**
     * Returns the timespan of the deadline.
     *
     * @return The timespan of the deadline.
     */
    @Override
    public String getTimespan() {
        return " (by: " + description.split("(?i)/by ")[1] + ")";
    }

    /**
     * Returns the icon of the deadline.
     *
     * @return The icon of the deadline.
     */
    @Override
    public String getTaskIcon() {
        return "D";
    }

}

