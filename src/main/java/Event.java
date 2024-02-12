public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    // Returns the status icon, description, start and end of the task
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + start + " To: " + end + ")";
    }
}