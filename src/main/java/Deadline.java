public class Deadline extends Task {
    public static final String DEADLINE_STATUS = "[D]";
    private String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_STATUS + super.toString() + " " + "(by: " + by + ")"; //superclass is task
    }
}
