package fredbot.task;

import fredbot.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws EmptyDescriptionException, DateTimeParseException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public String saveString() {
        return "D" + super.saveString() + " | " + by;
    }
}
