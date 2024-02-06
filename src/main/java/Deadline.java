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
     * Overwrites toString method in Java Object class.
     * 
     * @param None
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (by: " + this.end + ")";
    }
}
