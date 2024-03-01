package Nick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String symbol = "E";
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        fromDate = LocalDate.parse(from);
        toDate = LocalDate.parse(to);
    }

    public Event(String description, String from, String to, Boolean taskDone) {
        super(description);
        this.from = from;
        this.to = to;
        super.isDone = taskDone;
    }

    @Override
    public String toString() {
        return "\t" + "[" + symbol + "]" + "[" + super.getStatusIcon() + "] " + description + " (from: " + getFromDate() + " to: " + getToDate() + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getFromDate() {
        return fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String getToDate() {
        return toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
