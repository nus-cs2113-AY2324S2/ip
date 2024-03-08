/**
 * Event class represents an event task that has a description, start time and end time.
 * It extends the Task class.
 */
public class Event extends Task{
    protected String startOfEvent;
    protected String endOfEvent;

    /**
     * Constructs an Event object with the given description, start time and end time.
     *
     * @param description The description of the event.
     * @param startOfEvent The start time of the event.
     * @param endOfEvent The end time of the event.
     */
    public Event(String description, String startOfEvent, String endOfEvent) {
        super(description);
        this.startOfEvent = startOfEvent;
        this.endOfEvent = endOfEvent;
        this.taskType = "[E]";
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getStartOfEvent() {
        return startOfEvent;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getEndOfEvent() {
        return endOfEvent;
    }

    /**
     * Returns the event task in file format.
     *
     * @return The event task in file format.
     */
    public String taskInFileFormat() {
        boolean isDone = false;
        if (getStatusIcon().equals("[X]")) {
            isDone = true;
        }
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + getStartOfEvent()
           + "-" + getEndOfEvent() + "\n";
    }

    /**
     * Prints the event task with its type, status icon, description, and time frame.
     */
    @Override
    public void printTask() {
        System.out.println(taskType + getStatusIcon() + " " + description + " (from: " + startOfEvent
            + " to: " + endOfEvent + ")");
    }
}
