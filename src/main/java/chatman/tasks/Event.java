package chatman.tasks;

/**
 * Implements template for any task with start and end date-times.
 *
 * @author LWachtel1
 * */
public class Event extends Task {

    private String eventSymbol = "[E]";
    private String startDateTime;
    private String endDateTime;

    /**
     * Constructor for Event class
     *
     * @param description Description of task (that has specified start and end date-times)
     * @param from Starting date-time for event
     * @param to Ending date-time for event
     */
    public Event(String description, String from, String to, String command) {
        super(description, command);
        startDateTime = from;
        endDateTime = to;
    }


    /**
     * Returns Event subclass label, completion checkbox, task description, and start & end date-times as 1 String.
     *
     * @return String representation of Event: [E], [X] or [ ], task description, start & end date-time Strings.
     */
    @Override
    public String toString() {
        return eventSymbol + super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }


}
