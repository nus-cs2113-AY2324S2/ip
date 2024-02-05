public class Event extends Task{
    protected String at;

    protected String to;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getDescription() {
        return description + " (at: " + at + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + " to: " + to + ")";
    }
}
