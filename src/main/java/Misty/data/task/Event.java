package misty.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String from;
    private String to;
    private boolean isFromDateFormat;
    private boolean isToDateFormat;
    private LocalDate fromDate;
    private LocalDate toDate;


    public Event (String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;

        try {
            fromDate = LocalDate.parse(from);
            isFromDateFormat = true;
        } catch (DateTimeParseException e) {
            isFromDateFormat = false;
        }

        try {
            toDate = LocalDate.parse(to);
            isToDateFormat = true;
        } catch (DateTimeParseException e) {
            isToDateFormat = false;
        }

    }

    public String getFrom() {
        return from;
    }
    public String getFromDate() {
        return fromDate.getDayOfWeek() + ", " + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTo() {
        return to;
    }
    public String getToDate() {
        return toDate.getDayOfWeek() + ", " + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + getStatus() + " " + getTaskName() + " (from: " +
                (isFromDateFormat ? getFromDate() : getFrom()) + " to: " +
                (isToDateFormat ? getToDate() : getTo()) + ")";
    }
}
