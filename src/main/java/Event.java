public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description) {

        super(description);
        String[] firstPart = description.split(" /from ", 2);
        this.description = firstPart[0].substring(6).trim(); // Remove "event" command and trim spaces
        String[] secondPart = firstPart[1].split(" /to ", 2);
        this.from = secondPart[0];
        this.to = secondPart[1];
        this.eventType = "[E]";

    }
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
