public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        identity = "[D]";
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String getContent() {
        return super.getContent() + " (by: " + by + ")";
    }
}
