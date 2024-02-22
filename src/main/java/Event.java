class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getType() {
        return "[E]";
    }

    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}