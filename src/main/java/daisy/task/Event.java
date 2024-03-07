package daisy.task;

/**
 * The event class handles the creation and saving formatting of event tasks.
 */
public class Event extends Task {

    protected String fromDate;
    protected String toDate;

    /**
     * Creates an event instance.
     * @param taskName the task name of the event
     * @param fromDate the starting date of the event
     * @param toDate the ending date of the event
     */
    public Event(String taskName, String fromDate, String toDate){
        super(taskName);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns a String representation of the event in the format such that it is suitable for program printouts.
     * @return a String representation of the event suitable for printing
     */
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", (this.isDone)? "X":" ", this.taskName, this.fromDate, this.toDate);
    }

    /**
     * Returns a String representation of the event in the format such that it is suitable for loading and saving.
     * @return a String representation of the event suitable for loading and saving
     */
    public String save() {
        return String.format("E,%s,%s,%s,%s", (this.isDone)? "1":"0", this.taskName, this.fromDate, this.toDate);
    }

}
