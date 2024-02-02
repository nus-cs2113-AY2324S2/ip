public class Deadline extends Task {
    private final String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public Deadline(DeadLineArguments deadLineArguments) {
        super(deadLineArguments.taskName);
        this.by = deadLineArguments.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
