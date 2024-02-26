package jeff.tasks;

import jeff.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String PATTERN = "MMM d yyyy";
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String byFormatted = by.format(DateTimeFormatter.ofPattern(PATTERN));
        return "[D]" + super.toString() + "(by: " + byFormatted + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + by;
    }
}
