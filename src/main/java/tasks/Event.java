package tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }
  
    @Override
    public String toString(){
        return "[E]" + "[" + super.getStatusIcon() + "] " + this.description + " (from: " + from + " " + "to: " + to + ")";
    }
}
