package chris.tasktypes;

public class Event extends Task {
    protected String to;
    protected String from;

    public Event(String[] eventInfo, boolean isDone){
        super(eventInfo[0], isDone);
        this.from = eventInfo[1].trim();
        this.to = eventInfo[2].trim();
    }
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + ")" + " (to: " + to + ")";
    }

    public String saveString() { return "E|" + super.getStatusIcon() + "|" + super.saveString() + "|" + from + "|" + to; }

}
