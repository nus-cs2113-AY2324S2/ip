import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String byString;
    protected LocalDateTime byDateTime;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
        if (isDone) {
            markAsDone();
        }
    }

    @Override
    public String toString() {
        if (byDateTime != null) {
            return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + byString + ")";
        }
    }

    @Override
    public String toFileString() {
        if (byDateTime != null) {
            return "D | " + super.toFileString() + " | " + byDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return "D | " + super.toFileString() + " | " + byString;
        }
    }

}
