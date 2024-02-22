public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String convertToCommand() {
        return "event " + super.convertToCommand()
                + "/from " + this.from + "/to " + this.to;
    }
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " from " + this.from + " to " + this.to);
    }
}
