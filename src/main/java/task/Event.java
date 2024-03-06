package task;

import ui.Time;

/**
 * The Event class represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);

        try {
            this.from = Time.standardize(from);
        } catch (IllegalArgumentException e) {
            this.from = from;
        }

        try {
            this.to = Time.standardize(to);
        } catch (IllegalArgumentException e) {
            this.to = to;
        }
    }

    /**
     * Returns the string representation of the Event object.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
