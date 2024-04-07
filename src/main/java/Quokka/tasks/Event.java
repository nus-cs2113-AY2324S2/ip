package Quokka.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isDone      The completion status of the event.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Parses a string representation of an event task and returns an Event object.
     *
     * @param data   The string representation of the event task.
     * @param isDone The completion status of the event.
     * @return An Event object parsed from the string representation.
     */
    public static Event parseFromString(String data, boolean isDone) {
        String[] parts = data.split("\\(from: | to: ", 3);
        if (parts.length == 3) {
            String description = parts[0].trim();
            String from = parts[1];
            String to = parts[2].substring(0, parts[2].length() - 1);
            return new Event(description, from, to, isDone);
        }
        return null;
    }
}