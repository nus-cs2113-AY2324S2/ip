package uwunzhe.tasks;

public class Deadline extends Task {
    private String end;

    /**
     * Constructor for Deadline.
     * 
     * @param name The name of the deadline.
     * @param end The end time of the deadline.
     */
    public Deadline(String name, String end) {
        super(name);
        this.type = TaskType.DEADLINE.getType();
        this.end = end;
    }

    /**
     * Constructor for Deadline.
     * 
     * @param name The name of the deadline.
     * @param end The end time of the deadline.
     * @param isDone The status of the deadline.
     */
    public Deadline(String name, String end, boolean isDone) {
        super(name);
        this.type = TaskType.DEADLINE.getType();
        this.end = end;
        this.isDone = isDone;
    }

    /**
     * Prints the task and its status in one line.
     * 
     * @param None
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String completion = this.isDone ? "X" : " ";
        return String.format("[%s][%s] %s (by: %s)",
                this.type, completion, this.name, this.end);
    }
}
