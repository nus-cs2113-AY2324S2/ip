package tasks;

/** Type of task with a single date */
public class Deadline extends Task {
    /**
     * Constructs a deadline object, initially does not have a deadline yet.
     *
     * @param task The deadline task
     */
    public Deadline(String task) {
        super(task);
        this.haveDeadline = false;
        setTaskType("D");
    }
    protected boolean haveDeadline;
    protected String deadline;

    /**
     * Returns the deadline associated with the task.
     *
     * @return Deadline.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline and indicates that the task is a deadline as well.
     *
     * @param deadline Deadline associated with task.
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
        this.haveDeadline = true;
    }

    /** Prints the deadline information such as the description and deadline when presented by UI. */
    @Override
    public void print() {
        String additionalInfo;
        if (haveDeadline) {
            additionalInfo = " (by: " + deadline + ")";
        } else {
            additionalInfo = "";
        }
        super.print();
        System.out.println(getTaskDescription() + additionalInfo);
    }
}
