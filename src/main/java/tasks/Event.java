package tasks;

/**
 * Event task with details on the description, start and end conditions and whether it's complete.
 */
public class Event extends Task {

    protected String start;
    protected String end;


    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        start = startTime;
        end = endTime;
    }

    @Override
    public String getDescription() {
        return description + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileString() {
        return String.format("event|%s|%s|%s|%s", isDone ? "1" : "0", description, start, end);
    }

    @Override
    public String taskString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
