public class Deadline extends Task {
    protected String by; // Due date/time

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return  description + " (by: " + by + ")";
    }
}