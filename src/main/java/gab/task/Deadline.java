package gab.task;

public class Deadline extends Task {
    public static final String DEADLINE_STATUS = "[D]";
    private final String BY;

    public Deadline (String description, String by) {
        super(description);
        this.BY = by;
    }

    @Override
    public String toString() {
        return DEADLINE_STATUS + super.toString() + " " + "(by: " + BY + ")"; //superclass is task
    }

    @Override
    public String toFileFormat() {
        return DEADLINE_STATUS + super.toFileFormat() + " | " + "by: " + BY;
    }
}
