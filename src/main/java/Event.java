public class Event extends Task{

    /** Start date of Event */
    protected String start;
    /** End date of Event */
    protected String end;
    
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(), this.description, this.start, this.end);
    }
}
