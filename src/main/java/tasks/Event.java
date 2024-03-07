package tasks;

import baymax.Task;

public class Event extends Task {

    public Event(String description) {
        super(description);
        this.type = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    @Override
    public String toAddString() {
        return "[E]" + " [ ] " + description;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description;
    }
}
