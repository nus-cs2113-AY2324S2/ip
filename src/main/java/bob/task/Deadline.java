package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Task class. Used to represent Tasks with a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final LocalDateTime dueDate;


    private Deadline(String taskName, boolean isCompleted, LocalDateTime dueDate) {
        super(taskName, isCompleted);
        this.dueDate = dueDate;
    }

    public Deadline(String taskName, LocalDateTime dueDate) {
        super(taskName, false);
        this.dueDate = dueDate;
    }

    /**
     * Due date getter method.
     *
     * @return String representation of the Task due date.
     */
    public String getDueDate() {
        return this.dueDate.toString();
    }

    public Task markTaskAsComplete() {
        return new Deadline(this.taskName, true, this.dueDate);
    }

    public Task markTaskAsIncomplete() {
        return new Deadline(this.taskName, false, this.dueDate);
    }

    @Override
    public String toString() {
        String dueDateFormatted = dueDate.format(DATE_TIME_FORMATTER);
        return "[D]" + super.toString() + String.format(" (by: %s)", dueDateFormatted);
    }
}
