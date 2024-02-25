package quill.task;

import quill.exception.QuillException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task of type "Event" in the Quill application.
 * It extends the Task class and includes specific behavior for event tasks.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified task description, start date, and end date.
     *
     * @param description The description of the event task including start and end dates.
     * @throws QuillException If the task description is empty.
     * @throws DateTimeParseException If the format of start and end date is incorrect.
     */
    public Event(String description) throws QuillException, DateTimeParseException {
        super(description);
        int fromIndex = description.indexOf("/from");
        this.description = description.substring(0, fromIndex);
        if (this.description.isEmpty()) {
            throw new QuillException();
        }
        int toIndex = description.indexOf("/to");
        this.from = LocalDateTime.parse(description.substring(fromIndex + 5, toIndex - 1)
                        .replace('T', ' '),
                        DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm"));
        this.to = LocalDateTime.parse(description.substring(toIndex + 3)
                        .replace('T', ' '),
                        DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the event task for printing.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return "[E]" + super.toString() + "(from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    /**
     * Returns a string representation of the event task for storing.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String saveTask() {
        return "E | " + super.saveTask() + "/from " + from + " /to " + to;
    }

}
