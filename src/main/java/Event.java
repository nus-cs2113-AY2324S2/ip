public class Event extends Task{
    private String from;
    private String to;
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + getDescription() + " (from: " + from + " to: " + to + ")";
    }
}