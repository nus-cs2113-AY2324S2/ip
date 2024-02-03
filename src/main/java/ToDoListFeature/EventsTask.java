package ToDoListFeature;

public class EventsTask extends ToDo{
    private final String startDate;
    private final String endDate;

    /**
     * Constructor for EventsTask
     * @param name the name of the task
     * @param startDate the start date of the task
     * @param endDate the end date of the task
     */
    public EventsTask(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Get the full String representation of the task
     * @return the full String of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
