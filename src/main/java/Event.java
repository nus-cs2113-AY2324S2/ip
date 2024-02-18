public class Event extends Task {
    protected String taskType;
    protected String from;
    protected String to;
    protected String description;

    public Event(String description, String from, String to, String taskType) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
