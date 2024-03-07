package daisy.task;

/**
 * The deadline class handles the creation and saving formatting of deadline tasks.
 */
public class Deadline extends Task {

    protected String dueDate;

    /**
     * Creates a deadline instance.
     * @param taskName the task name of the deadline
     * @param dueDate the due date of the deadline
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    /**
     * Returns a String representation of the deadline in the format such that it is suitable for program printouts.
     * @return a String representation of the deadline suitable for printing
     */
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (this.isDone)? "X":" ", this.taskName, this.dueDate);
    }

    /**
     * Returns a String representation of the deadline in the format such that it is suitable for loading and saving.
     * @return a String representation of the deadline suitable for loading and saving
     */
    public String save() {
        return String.format("D,%s,%s,%s", (this.isDone)? "1":"0", this.taskName, this.dueDate);
    }

}
