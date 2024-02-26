package natsu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static natsu.util.Ui.printLine;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        try {
            LocalDateTime date = LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
