package bob;

/**
 * Represents a task in the list.
 */
public class Deadline extends Task {

    /**
     * The deadline of the task.
     */
    protected String by;

    /**
     * Constructor for the Deadline class.
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructor for the Deadline class.
     * @param stringRepresentation The string representation of the task.
     */
    public Deadline (String stringRepresentation) {
        super(stringRepresentation);
        String[] split = stringRepresentation.substring(7).split(" [(]");
        this.description = split[0];
        this.by = split[1].substring(4, split[1].length() - 1);
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return Ui.DEADLINE_ICON + super.getListItem() + " (by: " + by + ")";
    }
}