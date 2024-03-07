package alpaca.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */

public class Deadline extends Task {

    private LocalDateTime deadline;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    /**
     * Constructs a Deadline task with the specified name and deadline date.
     * The time is set to midnight by default.
     *
     * @param taskName The name or description of the deadline task.
     * @param deadline The date by which the task must be completed.
     */
    public Deadline (String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = LocalDateTime.of(deadline, LocalTime.parse("00:00"));
    }

    /**
     * Constructs a Deadline task with the specified name, deadline date, and time.
     *
     * @param taskName The name or description of the deadline task.
     * @param deadline The date by which the task must be completed.
     * @param time The time by which the task must be completed on the deadline date.
     */
    public Deadline (String taskName, LocalDate deadline, LocalTime time) {
        super(taskName);
        this.deadline = LocalDateTime.of(deadline, time);
    }

    /**
     * Constructs a Deadline task with the specified name and exact deadline date and time.
     * Used by storage class to restore the deadline.
     *
     * @param taskName The name or description of the deadline task.
     * @param deadline The exact date and time by which the task must be completed.
     */
    public Deadline (String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadline task, including its status, description, and deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        // Using formatter to convert deadline LocalDateTime to String
        String formattedDeadline = this.deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }

    /**
     * Formats the Deadline task for file saving, incorporating its completion status, description, and deadline.
     *
     * @return A string suitable for saving the Deadline task to a file.
     */
    @Override
    public String save() {
        // Using formatter to convert deadline LocalDateTime to String for saving
        String formattedDeadline = this.deadline.format(formatter);
        return "D | " + this.IntIsDone + " | " + this.description + " | " + formattedDeadline;
    }
}
