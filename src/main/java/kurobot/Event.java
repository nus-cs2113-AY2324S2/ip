package kurobot;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskName, String from, String to, boolean isMarked) {
        super("E", taskName, isMarked);
        this.from = from;
        this.to = to;
    }

    public String printTask() {
        return super.printTask() + "(from: " + this.from + " to: " + this.to +  ")";
    }
}
