package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private Event(String taskName, boolean isCompleted, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskName, isCompleted);
        this.startDate = startDate;
        this.endDate = endDate;
    }

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
        return this.startDate.toString();
    }

    /**
     * End date getter method.
     *
     * @return String representation of the Task end date.
     */
    public String getEndDate() {
        return this.endDate.toString();
    }

    public Task markTaskAsComplete() {
        return new Event(this.taskName, true, this.startDate, this.endDate);
    }

    public Task markTaskAsIncomplete() {
        return new Event(this.taskName, false, this.startDate, this.endDate);
    }

    @Override
    public String toString() {
        String startDateFormatted = startDate.format(DATE_TIME_FORMATTER);
        String endDateFormatted = endDate.format(DATE_TIME_FORMATTER);
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", startDateFormatted, endDateFormatted);
    }
}
