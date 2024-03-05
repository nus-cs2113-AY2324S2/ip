package dukeRobot.Tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from.split(" ",2)[1];
        this.to = to.split(" ",2)[1];
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to +")";
    }

}
