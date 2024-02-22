package beefy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;
    protected String from;
    protected String to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.fromDate = from;
        this.toDate = to;
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy',' HHmm"));
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy',' HHmm"));
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toDiskFormat() {
        return "E," + (this.getStatus() ? "TRUE," : "FALSE,") + description + ","
                + fromDate + ","
                + toDate + "\n";
    }
}
