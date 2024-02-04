public class Events extends Task{
    protected String from;
    protected String to;
    public Events(String description, String from, String to){
        super(description);
        identity = "[E]";
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public String getContent(){
        return super.getContent() + " (from: " + from + " to: " + to + ")";
    }
}
