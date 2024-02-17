package Quokka.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static Event parseFromString(String data, boolean isDone) {
        String[] parts = data.split("\\(from: | to: ", 3);
        if (parts.length == 3) {
            String description = parts[0].trim();
            String from = parts[1];
            String to = parts[2].substring(0, parts[2].length() - 1);
            return new Event(description, from, to, isDone);
        }
        return null;
    }
}