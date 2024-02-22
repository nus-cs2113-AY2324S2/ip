package task;

public class Deadline extends Task {
    private String deadline;

    public Deadline (String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskDetails() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + deadline + ")";
    }

    @Override
    public String getTaskAsString() {
        return "D | " + getDoneAsInteger() + " | " + description + " | " + deadline;
    }
}
