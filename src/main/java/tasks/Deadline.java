package tasks;

public class Deadline extends Task {
    public Deadline(String task) {
        super(task);
        this.isDeadline = false;
        setTaskType("D");
    }
    protected boolean isDeadline;
    protected String deadline;

    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
        this.isDeadline = true;
    }

    /** Prints the deadline information such as the description and deadline when presented by UI. */
    @Override
    public void print() {
        String additionalInfo;
        if (isDeadline) {
            additionalInfo = " (by: " + deadline + ")";
        } else {
            additionalInfo = "";
        }
        super.print();
        System.out.println(getTaskDescription() + additionalInfo);
    }
}
