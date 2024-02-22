package duke;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from.replaceFirst("from", "from:");
        this.to =  to.replaceFirst("to", "to:");
        this.taskId = "[E]";
    }

    @Override
    public String toString() {
        return taskId + this.getDoneStatus() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}
