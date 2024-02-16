package uwunzhe.tasks;

public class Event extends Task {
    private String start, end;

    /**
     * Constructor for Event.
     * 
     * @param name The name of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.type = TaskType.EVENT.getType();
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for Event.
     * 
     * @param name The name of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     * @param isDone The status of the event.
     */
    public Event(String name, String start, String end, boolean isDone) {
        super(name);
        this.type = TaskType.EVENT.getType();
        this.start = start;
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
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.type, completion, this.name, this.start, this.end);
    }
}
