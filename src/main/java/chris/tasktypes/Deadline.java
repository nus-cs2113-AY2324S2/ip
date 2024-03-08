package chris.tasktypes;

public class Deadline extends Task {
    protected String by;

    public Deadline(String[] deadlineInfo, boolean isDone){
        super(deadlineInfo[0], isDone);
        this.by = deadlineInfo[1].trim();
    }

    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    public String saveString() { return "D|" + super.getStatusIcon() + "|" + super.saveString() + "|" + by; }
}