package artemis.tasks;

public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;

    public Event(String taskName, String startDateTime, String endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (from: %s to: %s)", super.getStatusIcon(), super.taskName, startDateTime, endDateTime);
    }
}
