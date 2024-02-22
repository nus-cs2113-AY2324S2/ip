package todolist.task;

public class DeadLinesTask extends Task {
    private final String deadline;

    /**
     * Constructor for DeadLinesTask
     * @param name the name of the task
     * @param deadline the deadline of the task
     */
    public DeadLinesTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public DeadLinesTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String storeDataString() {
        return "D" + "|" + (this.isDone ? 1 : 0) + "|" + this.name + "|" + this.deadline;
    }

    /**
     * Get the full String representation of the task
     * @return the full String of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
