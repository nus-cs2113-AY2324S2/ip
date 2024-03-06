package bob;

/**
 * Represents an event in the list.
 */
public class Event extends Deadline {

    /**
     * The start of the event.
     */
    protected String start;

    /**
     * Constructor for the Event class.
     * @param description The description of the task.
     * @param start The start of the task.
     * @param by The deadline of the task.
     */
    public Event(String description, String start, String by, boolean isDone) {
        super(description, by, isDone);
        this.start = start;
    }

    /**
     * Constructor for the Event class.
     * @param stringRepresentation The string representation of the task.
     */
    public Event(String stringRepresentation) {
        super(stringRepresentation);
        String[] split = stringRepresentation.substring(7).split(" \\(from: ");
        this.description = split[0];
        String[] split2 = split[1].split(" to: ");
        this.start = split2[0];
        this.by = split2[1].substring(0, split2[1].length() - 1);
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return Ui.EVENT_ICON + super.getListItem() + " (from: " + start + " to: " + by + ")";
    }

}
