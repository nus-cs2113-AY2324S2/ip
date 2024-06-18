package Yoj.tasks;
public class Event extends Task {
    protected String start;
    protected String end;
    /**
     * Constructor for Event class with the given description(userInput), start time, and end time.
     *
     * @param description A string representing the event's description.
     * @param start A string representing the start time of the event.
     * @param end A string representing the end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    /**
     * Returns the type of the task, in this case "[E]" to represent an event
     *
     * @return A string indicating the type of the task.
     */
    public String taskType() {
        return "[E]";
    }
    /**
     * Provides a string representation of the event task, including its type and description.
     * It overrides the `toString` method in the superclass (Task).
     *
     * @return A string that represents the event task.
     */
    @Override
    public String toString() {
        return taskType() + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
}