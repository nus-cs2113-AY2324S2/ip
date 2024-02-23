package beefy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime byDate;
    protected String by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.byDate = by;
        this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy ',' HHmm"));
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDiskFormat() {
        return "D," + (this.getStatus() ? "TRUE," : "FALSE,") + description + ","
                + byDate + "\n";
    }
}
