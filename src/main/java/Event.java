class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    @Override
    public String toFileString() {
        return String.format("event/%s/%s/%s/%s", description, from, to, isDone ? "1" : "0");
    }
}


