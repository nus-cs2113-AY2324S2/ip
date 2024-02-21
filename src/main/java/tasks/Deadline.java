package tasks;

public class Deadline extends Task {

    public String deadline;

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
