public class Deadline extends Task {
    private String end;

    /**
     * Constructor for Deadline.
     * 
     * @param name The name of the deadline.
     * @return None
     */
    public Deadline(String name, String end) {
        super(name);
        this.type = "D";
        this.end = end;
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
