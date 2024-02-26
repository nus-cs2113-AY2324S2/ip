package Task;
/**
 * Represents the Event Task. A <code>Event</code> object corresponds to
 * a Event Task
 */
public class Event extends Task {
    private final String eventTime;
    public Event(String description, String eventTime){
        super(description);
        this.eventTime=eventTime.trim();
    }

    @Override
    public String get_date(){return eventTime;}
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + eventTime + ")";
    }
}
