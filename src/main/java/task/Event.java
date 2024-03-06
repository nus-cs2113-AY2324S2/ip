package task;

import ui.Time;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);

        try {
            this.from = Time.standardize(from);
        } catch (IllegalArgumentException e) {
            this.from = from;
        }

        try {
            this.to = Time.standardize(to);
        } catch (IllegalArgumentException e) {
            this.to = to;
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
