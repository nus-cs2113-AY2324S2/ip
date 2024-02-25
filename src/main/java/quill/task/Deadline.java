package quill.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import quill.exception.QuillException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description) throws QuillException, DateTimeParseException {
        super(description);
        int index = description.indexOf("/by");
        this.description = description.substring(0, index);
        if (this.description.isEmpty()) {
            throw new QuillException();
        }
        this.by = LocalDateTime.parse(description.substring(index + 3)
                        .replace('T', ' '),
                DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        String formattedBy = by.format(formatter);
        return "[D]" + super.toString() + "(by: " + formattedBy + ")";
    }

    @Override
    public String saveTask() {
        return "D | " + super.saveTask() + "/by " + by;
    }

}
