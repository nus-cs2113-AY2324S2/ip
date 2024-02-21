public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getDescription() {
        return description;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}