package fredbot.task;

import fredbot.exception.EmptyDescriptionException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
