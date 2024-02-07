public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String editDeadline(String description) {
        String[] s = description.split("/");
        return s[0].replace("deadline", "");
    }

    @Override
    public String toString() {
        return "[D]" + editDeadline(super.toString()) + " (by:" + by + ")";
    }

}
