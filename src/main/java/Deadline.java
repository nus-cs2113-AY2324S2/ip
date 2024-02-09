public class Deadline extends Task{
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
        return String.format("%s %s (%s)", super.toString(), super.description, deadline);
    }
}
