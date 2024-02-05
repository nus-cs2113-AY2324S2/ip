public class Event extends Task{
    protected String from;
    protected String to;

    public Event(int id, String content, boolean isDone, String from, String to) {
        super(id, content, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
