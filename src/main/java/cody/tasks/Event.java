package cody.tasks;

/**
 * The Event class represents a task with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the description of the task along with its start and end times.
     *
     * @return The description of the task with its start and end times.
     */
    @Override
    public String getDescription() {
        return description + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the type of the task, which is "E" for Event.
     *
     * @return The type of the task.
     */
    @Override
    public String getTaskType() {
        return "E";
    }
}
