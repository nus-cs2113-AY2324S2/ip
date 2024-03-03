package Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The <code>Event</code> class represents an event task in the task management application.
 * An event task includes a start time and an end time along with the basic task attributes
 * inherited from the Task class.
 * This class extends <code>Task</code> class to add specific functionality for event tasks.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     * As well as Parsing the input string to extract and set the description, start time,
     * and end time.
     *
     * @param input The raw input string containing the event's description and its start
     * and end times.
     */
    public Event(String input) {
        super(input);
        String[] firstPart = input.split(" /from ", 2);
        this.description = firstPart[0].substring(6).trim();
        String[] secondPart = firstPart[1].split(" /to ", 2);
        this.from = LocalDateTime.parse(secondPart[0].trim(), INPUT_FORMAT);
        this.to = LocalDateTime.parse(secondPart[1].trim(), INPUT_FORMAT);
        this.eventType = "[E]";
    }

    /**
     * Returns a string representation of the event task, including its type, status,
     * description, and formatted start and end times.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(OUTPUT_FORMAT) +
                " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Provides a string formatted for storage purposes, including the task type, status,
     * description, and start and end times in the input format.
     *
     * @return A string representation of the event task for storage purposes.
     */
    @Override
    public String toStorageString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                from.format(INPUT_FORMAT) + " to " + to.format(INPUT_FORMAT);
    }
}
