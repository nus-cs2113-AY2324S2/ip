package task;

import exception.AdamException;
import ui.Message;
import ui.Time;

import java.time.LocalDateTime;

/**
 * The Event class represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private String to;
    private String from;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) throws AdamException {
        super(description);
        try {
            LocalDateTime fromDateAndTime = Time.parseDateAndTime(from);
            LocalDateTime toDateAndTime = Time.parseDateAndTime(to);

            if (fromDateAndTime.isAfter(toDateAndTime)) {
                throw new AdamException(Message.EVENT_TIME_ERROR_MESSAGE);
            }

            this.from = Time.standardize(from);
            this.to = Time.standardize(to);
        } catch (IllegalArgumentException e) {
            this.from = from;
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
