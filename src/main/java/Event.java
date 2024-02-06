public class Event extends Task {
    private String start, end;

    /**
     * Constructor for Event.
     * 
     * @param name The name of the event.
     * @return None
     */
    public Event(String name, String start, String end) {
        super(name);
        this.type = "E";
        this.start = start;
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
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}
