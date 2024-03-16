import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with deadline.
 * Inherits from the Task class and adds deadline information.
*/
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a DeadlineTask with the specified description and deadline.
     *
     * @param description Description of the deadline task
     * @param deadline    Deadline by which the task needs to be completed
     */

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the DeadlineTask, including its type,
     * description, and deadline, formatted as "MMM d yyyy, h:mm a"
     *
     * @return A formatted string representing the DeadlineTask
     */
    @Override
    public String toString() {
        String formattedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }

    @Override
    public String toFileString() {
        return "D |"+ this.getDescription()  + String.format(" | %s | %s", super.toFileString(), this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }
}
