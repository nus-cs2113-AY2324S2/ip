public class Event extends Task{
    private String eventTime;
    Event(String description, String eventTime){
        super(description);
        this.eventTime=eventTime;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + eventTime + ")";
    }
}
