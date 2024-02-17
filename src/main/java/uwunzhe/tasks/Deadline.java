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

    /**
     * Returns the string representation of the task for storage.
     * 
     * @param delimiter The delimiter to be used.
     * @return String representation of the task for storage.
     */
    public String toStorageString(String delimiter) {
        int completion = this.isDone ? 1 : 0;
        return String.format("%s%s%d%s%s%s%s",
                this.type, delimiter, completion, delimiter, this.name, delimiter, this.end);
    }
}
