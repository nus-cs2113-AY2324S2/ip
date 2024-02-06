public class Deadline extends Task {
    protected String byDate;

    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Increments the class-level element totalNumTasks by 1.
     * Deadlines are also considered as tasks.
     *
     * @param name The name of the task to be created.
     * @param by The date of the task's deadline as a string.
     */
    public Deadline(String name, String by) {
        super(name);
        this.byDate = by;
    }

    @Override
    public String getTaskType () {
        return "D";
    }

    @Override
    public String getName () {
        return name + " (by: " + byDate + ")";
    }
}
