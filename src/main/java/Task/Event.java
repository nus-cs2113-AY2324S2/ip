package Task;

public class Event extends Task {
    private String eventTime;
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
