public class Event extends Task {
    /**
     * Constructor for Event.
     * 
     * @param name The name of the event.
     * @return None
     */
    public Event(String name) {
        super(name);
        this.type = "E";
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
