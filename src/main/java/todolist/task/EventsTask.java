package todolist.task;

public class EventsTask extends Task {
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

    public EventsTask(String name, boolean isDone, String startDate, String endDate) {
        super(name, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String storeDataString() {
        return "E" + "|" + (this.isDone ? 1 : 0) + "|" + this.name + "|" + this.startDate + "|" + this.endDate;
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
