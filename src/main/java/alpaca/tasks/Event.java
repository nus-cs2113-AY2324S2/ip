package alpaca.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    /**
     * Constructs an Event task with specified name, start and end times.
     *
     * @param taskName The name or description of the event task.
     * @param startDay The start date of the event.
     * @param startTime The start time of the event.
     * @param endDay The end date of the event.
     * @param endTime The end time of the event.
     */
    public Event (String taskName, LocalDate startDay, LocalTime startTime, LocalDate endDay, LocalTime endTime) {
        super(taskName);
        this.from = LocalDateTime.of(startDay, startTime);
        this.to = LocalDateTime.of(endDay, endTime);
    }

    /**
     * Constructs an Event task with specified name and start and end dates. Assumes time to be midnight.
     *
     * @param taskName The name or description of the event task.
     * @param startDay The start date of the event.
     * @param endDay The end date of the event.
     */
    public Event (String taskName, LocalDate startDay,  LocalDate endDay) {
        super(taskName);
        this.from = LocalDateTime.of(startDay, LocalTime.parse("00:00"));
        this.to = LocalDateTime.of(endDay, LocalTime.parse("00:00"));
    }

    /**
     * Constructs an Event task with specified name and exact start and end date-times.
     * Used by storage class to restore event task.
     *
     * @param taskName The name or description of the event task.
     * @param from The start date-time of the event.
     * @param to The end date-time of the event.
     */
    public Event (String taskName, LocalDateTime from,  LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its status, description, and start/end times.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        String formattedStartTime = this.from.format(formatter);
        String formattedEndTime = this.to.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }

    /**
     * Formats the Event task for file saving, incorporating its completion status, description, and start/end times.
     *
     * @return A string suitable for saving the Event task to a file.
     */
    @Override
    public String save() {
        String formattedStartTime = this.from.format(formatter);
        String formattedEndTime = this.to.format(formatter);
        return "E | " + this.IntIsDone + " | " + this.description + " | " + formattedStartTime +
            "| " + formattedEndTime;
    }
}