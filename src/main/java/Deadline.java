public class Deadline extends Task {
    private final String startTime;
    public Deadline(String description, String startTime) {
        super(description);
        this.startTime = startTime.split("by ", 2)[1];
    }
    public String getTypeDisplay() {
        return "[D]";
    }

    public String getData() {
        return this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription()
                + "(by: " + this.startTime + ")";
    }
}
