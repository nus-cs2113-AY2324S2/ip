package tasks;

public class Event extends Task {

    public String start;
    public String end;


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
