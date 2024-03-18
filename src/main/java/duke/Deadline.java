package duke;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.replaceFirst("by", "by:");
        this.taskId = "[D]";
    }

    @Override
    public String toString() {
        return taskId + this.getDoneStatus() + " " + description + " (" + by + ")";
    }
}
