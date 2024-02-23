package daisy.task;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (this.isDone)? "X":" ", this.taskName, this.dueDate);
    }

    public String save() {
        return String.format("D,%s,%s,%s", (this.isDone)? "1":"0", this.taskName, this.dueDate);
    }

}
