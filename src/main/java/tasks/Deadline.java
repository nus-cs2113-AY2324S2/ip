package tasks;

public class Deadline extends Task {
    public Deadline(String task) {
        super(task);
        this.isDeadline = false;
        setTaskType("D");
    }
    protected boolean isDeadline;
    protected String deadline;

    public boolean isDeadline() {
        return isDeadline;
    }
    public void setDeadline(boolean isDeadline) {
        this.isDeadline = isDeadline;
    }

    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
        this.isDeadline = true;
    }

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
