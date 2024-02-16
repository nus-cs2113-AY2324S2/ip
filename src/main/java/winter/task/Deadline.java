package winter.task;
public class Deadline extends Task {
    private static final String indent = "   ";
    protected String deadline;
    public Deadline (int order,boolean isMarked, String deadlineName, String deadline) {
        super(order,isMarked,deadlineName);
        this.deadline = deadline;
    }
    @Override
    public String getEndTime(){
        return deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        String typeCheckbox = "[D]";
        return indent + typeCheckbox + " " + this.doneCheckbox + this.taskName + " (by: " + this.deadline + ")";
    }

}
