package natsu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a specific due date or time.
 * A deadline task is a task that needs to be completed before a particular date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a {@code Deadline} task with the specified description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param by          The due date or time of the deadline task, represented as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            LocalDateTime date = LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    /**
     * Returns a string representation of the deadline task, including its status,
     * description, and due date/time.
     *
     * @return A string in the format "[D][status] description (by: due date/time)",
     * where "[D]" indicates a deadline task, "[status]" is the task's completion status
     * (marked as done or not done), and "description" and "due date/time" are the task's
     * description and due date/time, respectively.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
