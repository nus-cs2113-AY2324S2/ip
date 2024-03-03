package nick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with its duration /from and /to.
 */
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

        try {
            fromDate = LocalDate.parse(from);
            toDate = LocalDate.parse(to);
        }
        catch (DateTimeParseException e) {
            System.out.println(e.toString());
        }
    }

    public Event(String description, String from, String to, Boolean taskDone) {
        super(description);
        this.from = from;
        this.to = to;
        super.isDone = taskDone;

        try {
            fromDate = LocalDate.parse(from);
            toDate = LocalDate.parse(to);
        }
        catch (DateTimeParseException ignored) {
        }
    }

    @Override
    public String toString() {
        return "\t" + "[" + symbol + "]" + "[" + super.getStatusIcon() + "] " + description
                + " (from: " + getFromDate() + " to: " + getToDate() + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    /**
     * Returns the /from date of the event task in the format MMM dd yyyy.
     * If it is not possible, return the /from date as a string.
     *
     * @return /from date in the format MMM dd yyyy.
     */
    public String getFromDate() {
        try {
            return fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (NullPointerException e) {
            return this.from;
        }
    }

    /**
     * Returns the /to date of the event task in the format MMM dd yyyy.
     * If it is not possible, return the /to date as a string.
     *
     * @return /to date in the format MMM dd yyyy.
     */
    public String getToDate() {
        try {
            return toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (NullPointerException e) {
            return this.to;
        }
    }
}
