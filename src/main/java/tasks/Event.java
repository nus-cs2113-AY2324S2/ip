package tasks;

/**
 * Represents an Event-type task to be tracked by Dor. Subclass of Task
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for Event when no task name or task done status is provided.
     * Invokes the superclass constructor with no parameters
     *
     * @param from Date/time the event begins
     * @param to Date/time the event ends
     */
    public Event(String from, String to) {
        super();
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event when task name is provided but task done status is not.
     * Invokes the superclass constructor with one parameter
     *
     * @param name Name of the event
     * @param from Date/time the event begins
     * @param to Date/time the event ends
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event when task name and task done status are provided.
     * Invokes the superclass constructor with two parameters
     *
     * @param name Name of the event
     * @param isDone Whether the task is done or not
     * @param from Date/time the event begins
     * @param to Date/time the event ends
     */
    public Event(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the task-type "Event", represented by the String "E"
     *
     * @return String representation of the task being of "Event" type
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the date/time the event begins
     *
     * @return Date/time the event begins
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the date/time the event ends
     *
     * @return Date/time the event ends
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a String representation of the event for printing
     *
     * @return String representation of the event
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")");
    }
}
