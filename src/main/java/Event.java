public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public static Event addEvent(String task) {
        String[] taskSplit = task.split("/");
        String eventStart = taskSplit[1].replaceFirst("from", "").trim();
        String eventEnd = taskSplit[2].replaceFirst("to", "").trim();
        return new Event(taskSplit[0].trim(), eventStart, eventEnd);
    }
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " from " + this.from + " to " + this.to);
    }
}
