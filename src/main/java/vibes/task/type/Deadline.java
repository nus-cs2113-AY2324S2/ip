package vibes.task.type;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task{
    private final static String TASK_TYPE = "D";
    public static final String PRINT_FORMAT = "[D]%s (by: %s)";
    protected String by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description the description of the deadline task
     * @param by the deadline of the task
     */
    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return the deadline of the task
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the deadline of the task.
     *
     * @param by the deadline to be set
     */
    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getTaskType(){
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, super.toString(), by);
    }
}
