package Event;

public class Event extends Task {
    public String from;
    public String to;
    public Event(String input) {

        super(input);
        String[] firstPart = input.split(" /from ", 2);
        this.description = firstPart[0].substring(6).trim();
        String[] secondPart = firstPart[1].split(" /to ", 2);
        this.from = secondPart[0];
        this.to = secondPart[1];
        this.eventType = "[E]";

    }
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " to " + to;
    }
}
