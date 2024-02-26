public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return description + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    @Override
    public String toFileString() {
        return String.format("deadline/%s/%s/%s", description, by, isDone ? "1" : "0");
    }
}