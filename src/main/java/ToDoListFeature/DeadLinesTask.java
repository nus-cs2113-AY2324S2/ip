package ToDoListFeature;

public class DeadLinesTask extends ToDo{
    private String deadline;

    /**
     * Constructor for DeadLinesTask
     * @param name the name of the task
     * @param deadline the deadline of the task
     */
    public DeadLinesTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
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
