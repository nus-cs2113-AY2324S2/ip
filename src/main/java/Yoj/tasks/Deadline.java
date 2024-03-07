package Yoj.tasks;
public class Deadline extends Task {
    protected String by;

    public String taskType() {
        return "[D]";
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return taskType() + super.toString() + " (by: " + by + ")";
    }
}