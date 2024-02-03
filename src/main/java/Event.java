public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to:" + to + ")";
    }
}
