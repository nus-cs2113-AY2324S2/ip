package bean.task;

import bean.command.exception.NoValueException;

public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String description, String start, String end) throws NoValueException {
        super(description);
        if(start == null || end == null){
            throw new NoValueException();
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
