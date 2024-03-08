package tasks;

/** Most complex type of task with a timeframe between two dates. */
public class Event extends Task {

    /**
     * Constructs a event object, initially does not have event dates yet.
     *
     * @param task The event task
     */
    public Event(String task) {
        super(task);
        this.isEvent = false;
        setTaskType("E");
    }
    private boolean isEvent;

    /**
     * Marks the task as an event and not other tasks.
     *
     * @param event True if the task is an event, false otherwise.
     */
    public void setEvent(boolean event) {
        isEvent = event;
    }
    private String eventFrom, eventTo;

    public String getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(String eventFrom) {
        this.eventFrom = eventFrom;
    }

    public String getEventTo() {
        return eventTo;
    }

    public void setEventTo(String eventTo) {
        this.eventTo = eventTo;
    }

    /** Prints the event information such as the description, start time and end time when presented by UI. */
    @Override
    public void print() {
        String additionalInfo;
        if (isEvent) {
            additionalInfo = " (from: " + eventFrom + " to: " + eventTo + ")";
        } else {
            additionalInfo = "";
        }
        System.out.print('[' + getTaskType() + ']');
        System.out.print(isMarked() ? "[X] " : "[ ] ");
        System.out.println(getTaskDescription() + additionalInfo);
    }
}