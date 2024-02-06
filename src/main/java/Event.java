public class Event extends Task {

    protected String task;
    protected String from;
    protected String to;

    public Event(String description) {
        super(description);
        this.task = this.description.substring(
                "event".length(), 
                this.description.indexOf("/from")
                ).trim();
        this.from = this.description.substring(
                this.description.indexOf("/from")+"/from".length(), 
                this.description.indexOf("/to")
                ).trim();
        this.to = this.description.substring(
                this.description.indexOf("/to")+"/to".length()
                ).trim();
    }
    
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " 
                + this.task 
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}
