public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        if (isDone) {
            markAsDone();
        }
    }
    // Returns the status icon, description and by of the task
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by;
    }
}
