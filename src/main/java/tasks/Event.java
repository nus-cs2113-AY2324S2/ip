package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represent a task that has start and end times.
 */
public class Event extends Task {
    // ATTRIBUTES
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    // METHODS
    /**
     * Constructs Event.
     */
    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        try {
            fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException ignored) {}
        try {
            toDate = LocalDate.parse(to);
        } catch (DateTimeParseException ignored) {}
    }

    /**
     * Gets from.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets to.
     */
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        String fromString, toString;
        try {
            fromString = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
        } catch (NullPointerException e) {
            fromString = from;
        }

        try {
            toString = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
        } catch (NullPointerException e) {
            toString = to;
        }

        return "[E]" + super.toString()
            + " (from: " + fromString
            + " to: " + toString + ")";
    }
}
