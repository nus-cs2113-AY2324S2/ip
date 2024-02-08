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
     * 
     * @param None
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String completion = this.isDone ? "X" : " ";
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.type, completion, this.name, this.start, this.end);
    }
}
