public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String description) {
        super(description);
        int fromIndex = description.indexOf("/from");
        int toIndex = description.indexOf("/to");
        this.description = description.substring(0, fromIndex);
        this.from = description.substring(fromIndex + 5, toIndex);
        this.to = description.substring(toIndex + 3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
    }
}
