public class Events extends Task {

    protected String from;
    protected String to;

    public Events(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


    @Override
    public String toString() {
        return "[E] " + super.getStatusIcon() + " " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
