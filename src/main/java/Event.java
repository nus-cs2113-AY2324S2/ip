public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
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
