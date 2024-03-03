package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event Task class. Used to represent Tasks with a start date and end date.
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private Event(String taskName, boolean isCompleted, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskName, isCompleted);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor to create an Event Task Object.
     *
     * @param taskName  Name of Event Task.
     * @param startDate Start datetime of Event Task.
     * @param endDate   End datetime of Event Task.
     */
    public Event(String taskName, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskName, false);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Start date getter method.
     *
     * @return String representation of the Task start date.
     */
    public String getStartDate() {
        return startDate.toString();
    }

    /**
     * End date getter method.
     *
     * @return String representation of the Task end date.
     */
    public String getEndDate() {
        return endDate.toString();
    }

    public Task markTaskAsComplete() {
        return new Event(taskName, true, startDate, endDate);
    }

    public Task markTaskAsIncomplete() {
        return new Event(taskName, false, startDate, endDate);
    }

    @Override
    public String toString() {
        String startDateFormatted = startDate.format(Event.DATE_TIME_FORMATTER);
        String endDateFormatted = endDate.format(Event.DATE_TIME_FORMATTER);
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", startDateFormatted, endDateFormatted);
    }
}
