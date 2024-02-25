package todolist.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DeadLinesTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for DeadLinesTask
     * @param name the name of the task
     * @param deadline the deadline of the task
     */
    public DeadLinesTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Constructor for DeadLinesTask
     * @param name the name of the task
     * @param isDone the status of the task
     * @param deadline the deadline of the task
     */
    public DeadLinesTask(String name, boolean isDone, LocalDateTime deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    /**
     * Get the deadline of the task
     * @return the deadline of the task
     */
    @Override
    public String storeDataString() {
        return "D" + "|" + (this.isDone ? 1 : 0) + "|" + this.name + "|" + this.deadline;
    }

    /**
     * Get the full String representation of the task
     * @return the full String of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM dd, uuuu hh:mm:ss a", Locale.ENGLISH);
        String formattedDeadline = this.deadline.format(outputFormatter);
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }

}
