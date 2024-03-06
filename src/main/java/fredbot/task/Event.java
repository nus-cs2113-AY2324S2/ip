package fredbot.task;

import fredbot.exception.EmptyDescriptionException;

/**
 * Represents an event that the user was to add to their task list.
 * An Event is a type of Task that also includes event from and to dates.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs the Event object.
     *
     * @param description Description of the event.
     * @param from The from date of the event.
     * @param to The to date of the event.
     * @throws EmptyDescriptionException If the description of the event is empty.
     */
    public Event(String description, String from, String to) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the event to a string for printing purposes.
     *
     * @return The done status, description, from and to dates of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the event to a string for saving purposes.
     *
     * @return The done status, description, from and to dates of the event.s
     */
    public String saveString() {
        return "E" + super.saveString() + " | " + from + " | " + to;
    }
}
