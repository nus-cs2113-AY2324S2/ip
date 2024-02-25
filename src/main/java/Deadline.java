public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String by) {
        super(description);
        this.deadline = by;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String saveString() {
        return getType() + " | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + getDeadline();
    }
}
