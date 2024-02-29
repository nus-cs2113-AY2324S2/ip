package suv.Task;

import suv.Task.Task;

/**
 * The Event class represents a task that occurs within a specific time frame.
 * It inherits from the Task class and adds functionality specific to Event tasks.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new Event object with the specified description, starting time, and ending time.
     *
     * @param description The description of the Event task.
     * @param from The starting time of the Event.
     * @param to The ending time of the Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * The representation includes the task type identifier, description, and time frame of the event.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to +")";
    }
}