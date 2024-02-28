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

    public String getTo() {
        return to;
    }

    public String getFullFromDate() {
        return fromDate.getDayOfWeek() + ", "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getFullToDate() {
        return toDate.getDayOfWeek() + ", "
                + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + getStatus() + " " + getTaskName() + " (from: "
                + (isFromDateFormat ? getFullFromDate() : getFrom()) + " to: "
                + (isToDateFormat ? getFullToDate() : getTo()) + ")";
    }

    public boolean isBetweenDate(LocalDate localDate) {
        if (isFromDateFormat && isToDateFormat) {
            return ((localDate.isAfter(fromDate) && localDate.isBefore(toDate))
                    || localDate.equals(fromDate) || localDate.equals(toDate));
        }
        return false;
    }
}