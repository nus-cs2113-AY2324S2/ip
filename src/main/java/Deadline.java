public class Deadline extends Task {
    protected String type = "D";
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", super.toString(), deadline);
    }
    @Override
    public String toString(boolean isFormatCache) {
        return String.format("%s | %s", super.toString(isFormatCache), deadline);
    }
}
