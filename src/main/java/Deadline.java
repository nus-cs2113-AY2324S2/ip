public class Deadline extends Task{
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public void setBy(String newBy) {
        this.by = newBy;
    }

    public static Deadline addDeadline(String task) {
        String[] taskSplit = task.split("/by");
        taskSplit[1] = taskSplit[1].replace("/by", "").trim();
        return new Deadline(taskSplit[0].trim(), taskSplit[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + this.by;
    }

}
