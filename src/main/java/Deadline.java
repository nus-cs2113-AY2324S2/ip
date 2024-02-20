public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][X] " + super.description + " (by: " + by + ")": "[D][ ] " + super.description + " (by: " + by + ")"); // mark done task with X
    }

    @Override
    public String printFileFormat() {
        return super.printFileFormat() + " | " + this.by;
    }
}