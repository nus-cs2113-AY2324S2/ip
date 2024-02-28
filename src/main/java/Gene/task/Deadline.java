package Gene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        taskType = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter
                .ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    public String toFileString() {
        return super.toFileString() + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

