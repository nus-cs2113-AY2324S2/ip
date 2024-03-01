public class Event extends Task {

    private String startDateTime;
    private String endDateTime;

    /**
     * Constructor for Event
     *
     * @param description task description
     * @param from starting date-time for task
     * @param to ending date-time for task
     */
    public Event(String description, String from, String to) {
        super(description);
        startDateTime = from;
        endDateTime = to;
    }

    /**
     * Returns task description, whether it has been completed , label for Event subclass
     * and its start and end date-times
     *
     * @return [E] label, completion status of task, task description, start and end date-times as string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }


}
