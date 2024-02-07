public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(from:" + from + " to:" + to + ")";
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }
}
