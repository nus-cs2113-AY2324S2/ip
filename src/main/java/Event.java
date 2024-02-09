public class Event extends Task {

    protected String from;
    protected String to;

    //Deadline subclass of Task superclass
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String statusMark = this.isCompleted() ? "x" : " "; // Mark with 'x' if completed
        return "[E]" + "[" + statusMark + "] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
