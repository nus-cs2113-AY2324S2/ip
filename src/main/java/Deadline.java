public class Deadline extends Task {

    protected String by;

    public Deadline(String description) {
        super(description);
        String[] parts = description.split(" /by ", 2);
        this.description = parts[0].substring(9).trim(); // Remove "deadline" and trim
        this.by = parts[1];
        this.taskType = "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}