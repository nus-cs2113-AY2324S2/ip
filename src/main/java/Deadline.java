public class Deadline extends Task {
    protected String by;

    public Deadline(String[] deadlineInfo) {
        super(deadlineInfo[0]);
        this.by = deadlineInfo[1];
    }

    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}