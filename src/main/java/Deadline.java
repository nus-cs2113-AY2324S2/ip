public class Deadline extends Task{

    protected String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (this.isDone)? "X":" ", this.taskName, this.dueDate);
    }

}
