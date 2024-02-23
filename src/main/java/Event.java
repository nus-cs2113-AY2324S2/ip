import java.util.NoSuchElementException;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description) {
        super(description);
        extractDescription(description);
        this.taskType = "[E]";
    }

    private void extractDescription(String description) {
        String[] parts = description.split(" /from ", 2);
        if (description.trim().equalsIgnoreCase("event")) {
            throw new IllegalArgumentException();
        }
        this.description = parts[0].substring(6).trim(); // Remove "event" command and trim spaces
        if (parts.length != 2) {
            throw new NoSuchElementException();
        }
        parts = parts[1].split(" /to ", 2);

        this.from = parts[0];
        this.to = parts[1];
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString();
    }
}
