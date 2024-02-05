public class Event extends Task{
    protected String event;

    public Event(String description, String event) {
        super(description);
        this.event = event;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[E][X] " + super.description + " (" + event + ")": "[E][ ] " + super.description + " (" + event + ")"); // mark done task with X
    }
}
