public class Event extends Task {

    protected String to;
    protected String from;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]"  +"[" + this.getStatusIcon()+ "] " + super.toString() + " (from: " + from + " to: " + to +")";
    }

    @Override
    public String toFileString() {
        return "E|" + super.toFileString() + "|" + from + "|" + to; // Prefix with "E" to indicate Event
    }
}