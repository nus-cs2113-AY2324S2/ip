public class Event extends Task {
    protected String to;
    protected String from;

    public Event(String[] eventInfo) {
        super(eventInfo[0]);
        this.from = eventInfo[1];
        this.to = eventInfo[2];
    }
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + ")" + " (to: " + to + ")";
    }
}
