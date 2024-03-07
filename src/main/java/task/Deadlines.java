package task;

/**
 * The Deadline class represents a task that needs to be done before a specific date/time.
 */
public class Deadlines extends Tasks{
    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
