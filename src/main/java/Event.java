public class Event extends Task {
    private static final int TASK_START_INDEX = 6;
    private String from;
    private String to;

    public Event(String line) {
        super(line.substring(TASK_START_INDEX, line.indexOf("/from")));
        from = line.substring(line.indexOf("/from") + 6, line.indexOf("/to"));
        to = line.substring(line.indexOf("/to") + 4);
    }

    @Override
    public String getDetails() {
        return ("[E]" + super.getDetails() + "(from: " + from + "to: " + to + ")");
    }
}
