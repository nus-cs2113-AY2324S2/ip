public class Deadline extends Task {
    protected String by;
    protected String description;
    protected String taskType;

    public Deadline(String description, String by, String taskType) {
        super(description);
        this.by = by;
        this.taskType = taskType;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }


}

