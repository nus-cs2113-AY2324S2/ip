package misty.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatus() + " " + getTaskName() + " (by: " + getBy() + ")";
    }
}
