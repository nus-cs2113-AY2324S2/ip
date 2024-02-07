public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String editEvent(String description) {
        String[] s = description.split("/");
        return s[0].replace("event", "");
    }

    @Override
    public String toString() {
        return "[E]" + editEvent(super.toString()) + "(from:" + from + " to:" + to + ")";
    }
}
