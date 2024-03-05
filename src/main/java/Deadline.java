public class Deadline extends Task {
    protected String by;
    protected String description;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

