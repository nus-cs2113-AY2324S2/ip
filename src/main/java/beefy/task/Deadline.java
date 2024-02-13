package beefy.task;

public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDiskFormat() {
        return "D," + (this.getStatus() ? "TRUE," : "FALSE,") + description + ","
                + by + "\n";
    }
}
