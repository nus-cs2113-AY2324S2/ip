package quill.task;

import quill.exception.EmptyDateException;
import quill.exception.QuillException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return "[E]" + super.toString() + "(from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + super.saveTask() + "/from " + from + " /to " + to;
    }
}
