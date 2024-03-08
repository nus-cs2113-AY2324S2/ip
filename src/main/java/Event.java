/**
 * The Event class represents a task with a start and end time.
 * It extends the Task class and adds start and end date and time properties.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new Event object with the given description, start time, and end time.
     * @param description The task description
     * @param start The start date and time in the format "yyyy-MM-dd HHmm"
     * @param end The end date and time in the format "yyyy-MM-dd HHmm"
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }


    /**
     * Gets the start date and time.
     * @return The start date and time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Gets the end date and time.
     * @return The end date and time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns a string representation of the Event task.
     * @return The string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + description + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
    }

    /**
     * Returns a string representation of the Event task suitable for writing to a file.
     * @return The string representation
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}