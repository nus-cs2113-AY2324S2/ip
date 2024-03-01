/**
 * Defines a specific task with a "From" date and a "To" date.
 */
public class Event extends Task{
    protected String startTime;
    protected String endTime;


    /**
     * Returns the event's description in the proper format displayed in the list.
     *
     * @return Formatted event description.
     */
    @Override
    public String getDescription(){
        return ("[E][" + this.getStatusIcon() + "] " + this.description + " (from: " + startTime + " to: " + endTime +
                ")");
    }


    /**
     * Creates instance of an event task with a description, start time, ending time and completeness.
     *
     * @param description Description of the task.
     * @param start Starting time of the task.
     * @param ending Ending time of the task.
     * @param isCompleted Completeness of the task.
     */
    public Event(String description, String start, String ending, boolean isCompleted) {
        super(description);
        this.startTime = start;
        this.endTime = ending;
        this.isDone = isCompleted;
    }


    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String time) {
        this.startTime = time;
    }

    public String getEndTime() {
        return this.endTime;
    }


    public void setEndTime(String time) {
        this.endTime = time;
    }
}
