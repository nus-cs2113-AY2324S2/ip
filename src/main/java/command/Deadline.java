package command;

public class Deadline extends Task {
    protected String symbol = "D";
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean taskDone) {
        super(description);
        this.deadline = deadline;
        super.isDone = taskDone;
    }
    @Override
    public String toString() {
        return "\t" + "[" + symbol + "]" + "[" + super.getStatusIcon() + "] " + description + " (by: " + deadline + ")";
    }

    public String getDeadline() {
        return deadline;
    }
}
