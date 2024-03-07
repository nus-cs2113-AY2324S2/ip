package tasks;

public class Event extends Task {
    public Event(String task) {
        super(task);
        this.isEvent = false;
        setTaskType("E");
    }
    private boolean isEvent;

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
        super.print();
        System.out.println(getTaskDescription() + additionalInfo);
    }
}
