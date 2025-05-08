package misty.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Creates Events.
 */
public class Event extends Task {
    /**
     * Start date of event as a string.
     */
    private String from;
    /**
     * End date of event as a string.
     */
    private String to;
    private boolean isFromDateFormat;
    private boolean isToDateFormat;
    /**
     * Start date of event as LocalDate type.
     */
    private LocalDate fromDate;
    /**
     * End date of event as LocalDate type.
     */
    private LocalDate toDate;

    /**
     * Constructs Event object.
     *
     * @param taskName Name of event.
     * @param from Start date of event.
     * @param to End date of event.
     */
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

    /**
     * Returns start date of event.
     *
     * @return Start date of event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns end date of event.
     *
     * @return End date of event.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns start date of event as LocalDate.
     *
     * @return Start date of event as LocalDate.
     */
    public String getFullFromDate() {
        return fromDate.getDayOfWeek() + ", "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns end date of event as LocalDate.
     *
     * @return End date of event as LocalDate.
     */
    public String getFullToDate() {
        return toDate.getDayOfWeek() + ", "
                + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns string of how event should be printed to screen.
     *
     * @return String to be printed to screen.
     */
    @Override
    public String toString() {
        return "[E]" + getStatus() + " " + getTaskName() + " (from: "
                + (isFromDateFormat ? getFullFromDate() : getFrom()) + " to: "
                + (isToDateFormat ? getFullToDate() : getTo()) + ")";
    }

    /**
     * Returns whether date passed as parameter is between start date and end date of event inclusive.
     *
     * @param localDate Date to be compared to start date and end date of event.
     * @return True if date is in between start date and end date of event, else false.
     */
    public boolean isBetweenDate(LocalDate localDate) {
        if (isFromDateFormat && isToDateFormat) {
            return ((localDate.isAfter(fromDate) && localDate.isBefore(toDate))
                    || localDate.equals(fromDate) || localDate.equals(toDate));
        }
        return false;
    }
}