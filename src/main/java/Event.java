public class Event extends Task {
    private static int taskStartIndex = 6;

    public Event(String line) {
        super(line.substring(taskStartIndex, line.indexOf("/from")));
    }
}
