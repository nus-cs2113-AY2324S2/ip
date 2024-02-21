package Blue;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toSaveTextFormat() {
        String done = (getStatusIcon().equals("X") ? "1" : "0");
        return "D|" + done + "|" + getDescription() + "|" + by + System.lineSeparator();
    }

    @Override
    public String toString() {
        return super.toString() + " by: " + by;
    }
}
