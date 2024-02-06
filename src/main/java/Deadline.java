public class Deadline extends Task {
    protected String by;
    public Deadline(String description) {
        super(description);
        String[] parts = description.split(" /by ", 2);
        this.description = parts[0].substring(9).trim(); // Remove "deadline" command and trim spaces
        this.by = parts[1];
        this.eventType = "[D]";
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}