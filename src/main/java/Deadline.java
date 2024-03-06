public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]"  +"[" + this.getStatusIcon()+ "] " + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D|" + super.toFileString() + "|" + by; // Prefix with "D" to indicate Deadline
    }
}

