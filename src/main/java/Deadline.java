public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "[D]";
    }
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public void printTask() {
        System.out.println(taskType + getStatusIcon() + " " + description + " (by:" + by + ")");
    }
}

