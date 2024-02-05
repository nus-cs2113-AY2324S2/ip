public class Deadline extends Task {
    /**
     * Constructor for Deadline.
     * 
     * @param name The name of the deadline.
     * @return None
     */
    public Deadline(String name) {
        super(name);
        this.type = "D";
    }

    /**
     * Prints the task and its status in one line.
     * Overwrites toString method in Java Object class.
     * 
     * @param None
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
