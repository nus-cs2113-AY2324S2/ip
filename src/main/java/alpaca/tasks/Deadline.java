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

    public Deadline (String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = LocalDateTime.of(deadline, LocalTime.parse("00:00"));
    }

    public Deadline (String taskName, LocalDate deadline, LocalTime time) {
        super(taskName);
        this.deadline = LocalDateTime.of(deadline, time);
    }

    public Deadline (String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        // Using formatter to convert deadline LocalDateTime to String
        String formattedDeadline = this.deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }

    @Override
    public String save() {
        // Using formatter to convert deadline LocalDateTime to String for saving
        String formattedDeadline = this.deadline.format(formatter);
        return "D | " + this.IntIsDone + " | " + this.description + " | " + formattedDeadline;
    }
}
