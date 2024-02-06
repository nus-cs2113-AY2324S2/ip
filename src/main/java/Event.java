public class Event extends Task {
    protected String from;
    protected String to;

    public Event (String description) {
        super(description);
        int fromIndex = description.indexOf("/");
        String time = description.substring(fromIndex + 1);
        int toIndex = time.indexOf("/") + fromIndex + 1;
        String taskName = description.substring(0, fromIndex - 1);
        setTaskName(taskName);
        from = description.substring(fromIndex + 6, toIndex - 1);
        to = description.substring(toIndex + 4);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
