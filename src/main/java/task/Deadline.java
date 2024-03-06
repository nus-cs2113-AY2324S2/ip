package task;

/**
 * Task Type-Deadline
 */
public class Deadline extends Task {
    protected String by;
    protected boolean canSearchDate;

    public boolean getCanSearchDate() {
        return canSearchDate;
    }

    public void setCanSearchDate(boolean canSearchDate) {
        this.canSearchDate = canSearchDate;
    }

    public Deadline(String description, String by) {
        super(description);
        identity = "[D]";
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String toString() {
        return super.toString() + "  (by: " + by + ")";
    }
}
