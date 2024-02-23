public class DeadLine extends Task {
    protected String by;

    public String toFileFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }
    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
