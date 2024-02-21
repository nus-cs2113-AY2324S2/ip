public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D] " + super.getStatusIcon() + " " + super.toString() + " (by: " + by + ")";
    }
}
