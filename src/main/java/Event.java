public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Task fromString(String line) throws MissingParameterException {
        String event = line.replace("event", "");
        String[] segments = event.split("/from");
        if(segments.length < 2) {
            throw new MissingParameterException("event");
        }
        String eventName = segments[0].trim();
        if(eventName.isEmpty()) {
            throw new MissingParameterException("event");
        }
        segments = segments[1].split("/to");
        if(segments.length < 2) {
            throw new MissingParameterException("event");
        }
        String from = segments[0].trim();
        String to = segments[1].trim();
        if(from.isEmpty() || to.isEmpty()) {
            throw new MissingParameterException("event");
        }
        return new Event(eventName, from, to);
    }

    public String toFileString() {
        return "E|" + (isDone ? 1 : 0) + "|" + description + "|" + from + "|" + to;
    }

    public static Task fromFileString(String line) {
        String[] items = line.split("\\|");
        Event event = new Event(items[2], items[3], items[4]);
        if(items[1].equals("1")) {
            event.setIsDone();
        }
        return event;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
