package cody.tasks;

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
    public String getTaskType() {
        return "D";
    }
}
