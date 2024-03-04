package sayo;

/**
 * Represents an event with a start and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The task's description.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the event, including its start and end times.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns the string representation of the Event task formatted for file storage.
     * Prefixes the base task file format with "E |" to denote an event task.
     *
     * @return A string formatted for saving to a file.
     */
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + start + " | " + end;
    }

    /**
     * Creates an Event object from a string format.
     * Parses the string based on the predefined format and creates an Event object accordingly.
     *
     * @param fileFormat A string representing the task in file format.
     * @return A new Event object populated with the data from the fileFormat string.
     */
    public static Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String start = parts[3].trim();
        String end = parts[4].trim();

        Event event = new Event(description, start, end);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }

}
