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

    public String getDeadline() {
        return deadline;
    }
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
