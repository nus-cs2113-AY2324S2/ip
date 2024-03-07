package kurobot;

/**
 * A type of task that contains start and end timing.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Stores the given task name, start and end timings, and marking status.
     *
     * @param taskName Name of the task.
     * @param from Start time.
     * @param to End time.
     * @param isMarked Marked or not marked.
     */
    public Event(String taskName, String from, String to, boolean isMarked) {
        super("E", taskName, isMarked);
        this.from = from;
        this.to = to;
    }

    public String printTask() {
        return super.printTask() + "(from: " + this.from + " to: " + this.to +  ")";
    }
}
