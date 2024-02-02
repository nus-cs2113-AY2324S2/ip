public class EventArguments extends CommandArguments {
    public String taskName;
    public String start;
    public String end;

    public EventArguments(String taskName, String start, String end) {
        this.taskName = taskName;
        this.start = start;
        this.end = end;
    }
}
