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
     * @param input The description of the event task including start and end dates.
     * @throws QuillException If the task description is empty.
     * @throws DateTimeParseException If the format of start and end date is incorrect.
     */
    public Event(String input) throws QuillException {
        super(input);
        int fromIndex = input.indexOf("/from");
        this.description = input.substring(0, fromIndex);
        if (this.description.isEmpty()) {
            throw new QuillException("No empty descriptions allowed for event. Fill it in!");
        }
        int toIndex = input.indexOf("/to");
        formatTime(input, fromIndex, toIndex);
        if (to.isBefore(from)) {
            throw new QuillException("Quick tip: 'from' should precede 'to', unless you've mastered time bending.");
        }
    }

    /**
     * Parse the format of from and to from String to LocalDateTime.
     *
     * @param fromIndex The index of /from.
     * @param toIndex The index of /to.
     */
    public void formatTime(String input, int fromIndex, int toIndex) throws DateTimeParseException {
        this.from = LocalDateTime.parse(input.substring(fromIndex + 5, toIndex - 1)
                        .replace('T', ' '),
                        DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm"));
        this.to = LocalDateTime.parse(input.substring(toIndex + 3)
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
