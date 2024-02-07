public class Deadline extends Task {
    protected String symbol = "D";
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "\t" + "[" + symbol + "]" + "[" + super.getStatusIcon() + "] " + description + " (by: " + deadline + ")";
    }
}
