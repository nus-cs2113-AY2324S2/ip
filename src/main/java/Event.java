public class Event extends Task {
    protected String startDate;
    protected String endDate;
    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Increments the class-level element totalNumTasks by 1.
     * Events are also considered as tasks.
     *
     * @param name The name of the task to be created.
     * @param start The starting time/date of the task.
     * @param end The ending time/date of the task.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getName () {
        return name + "(from: " + startDate + " to: " + endDate + ")";
    }
}
