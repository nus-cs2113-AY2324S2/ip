package tasks;

/**
 * Deadline task with details on the description, deadline condition and whether it's complete.
 */
public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        deadline = date;
    }

    @Override
    public String getDescription() {
        return description + " (by: " + deadline + ")";
    }

    @Override
    public String toFileString() {
        return String.format("deadline|%s|%s|%s", isDone ? "1" : "0", description, deadline);
    }

    @Override
    public String taskString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
