package artemis.tasks;

/**
 * Represents an event based on task
 */
public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;

    /**
     * Creates an Event task with the given task name, start and end date time
     * @param taskName The name of the task
     * @param startDateTime The start date time of the event as String
     * @param endDateTime The end date time of the event as String
     */
    public Event(String taskName, String startDateTime, String endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns a formatted string representation of the Event
     * @return Formatted string of Event
     */
    @Override
    public String toString() {
        return String.format("[E] [%s] %s (from: %s to: %s)", super.getStatusIcon(), super.taskName, startDateTime, endDateTime);
    }

    /**
     * Gets the start date time
     * @return String of assigned start date time
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     * Gets the end date time
     * @return String of assigned end date time
     */
    public String getEndDateTime() {
        return endDateTime;
    }
}
