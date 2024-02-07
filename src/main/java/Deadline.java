public class Deadline extends Task {
    private String by; // Deadline time

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }
}

