public class Event extends Task {
    public static final String TYPE = "E";
    private String from;
    private String to;

    public Event(String from, String to) {
        super();
        this.from = from;
        this.to = to;
    }

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")");
    }
}
