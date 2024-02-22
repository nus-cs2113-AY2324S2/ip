public class Deadline extends Task{
    private String task;
    private String by;
    public Deadline(String task, String by)
    {
        super(task);
        this.by=by;
    }
    public Deadline(String task, String by, boolean isComplete)
    {
        super(task,isComplete);
        this.by=by;
    }
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    protected String getTaskType() {
        return "D";
    }
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s ", getTaskType(), this.isComplete() ? 1 : 0, this.getTask(),this.by);
    }
}
