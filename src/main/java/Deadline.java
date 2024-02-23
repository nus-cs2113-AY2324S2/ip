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

    public String taskInFileFormat() {
        boolean isDone = false;
        if (getStatusIcon().equals("[X]")) {
            isDone = true;
        }
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + getBy() + "\n";
    }
    @Override
    public void printTask() {
        System.out.println(taskType + getStatusIcon() + " " + description + " (by:" + by + ")");
    }
}

