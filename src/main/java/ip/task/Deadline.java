package ip.task;

public class Deadline extends Task {
    private static final int TASK_START_INDEX = 9;
    private String by;

    public Deadline(String line) {
        super(line.substring(TASK_START_INDEX, line.indexOf("/by")));
        by = line.substring(line.indexOf("/by") + 4);
    }

    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
    }

    @Override
    public String getDetails() {
        return ("[D]" + super.getDetails() + "(by: " + by + ")");
    }
}
